@file:OptIn(FlowPreview::class)

package com.jabama.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.common.Resource
import com.jabama.domain.repositories.GetRepositoriesUseCase
import com.jabama.domain.token.ClearTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val clearTokenUseCase: ClearTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SearchEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            state
                .map { it.searchQuery }
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    getRepositoriesUseCase(query.orEmpty())
                }
                .onStart {
                    _state.update { it.copy(apiIsLoading = true) }
                }
                .collect { results ->
                    when (results) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isFailed = true,
                                    errorMessage = results.exception.toString(),
                                    apiIsLoading = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    apiIsLoading = false,
                                    isFailed = false,
                                    searchedRepository = results.data
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onEvent(event: SearchEvent) {
        viewModelScope.launch {
            when (event) {
                SearchEvent.Logout -> {
                    clearTokenUseCase()
                    navigateToLogin()
                }

                is SearchEvent.SearchQueryChanged -> {
                    _state.update {
                        it.copy(
                            searchQuery = event.query
                        )
                    }
                }

                SearchEvent.OnClearQueryClick -> {
                    _state.update {
                        it.copy(
                            searchQuery = null
                        )
                    }
                }

                SearchEvent.OnRetryClick -> retrySearch()
            }
        }
    }

    private suspend fun navigateToLogin() {
        _effect.send(SearchEffect.NavigateToLogin)
    }

    private fun retrySearch() {
        viewModelScope.launch {
            getRepositoriesUseCase(_state.value.searchQuery.orEmpty())
                .collect { results ->
                    when (results) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isFailed = true,
                                    errorMessage = results.exception.toString(),
                                    apiIsLoading = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    apiIsLoading = false,
                                    isFailed = false,
                                    searchedRepository = results.data
                                )
                            }
                        }
                    }
                }
        }
    }

}