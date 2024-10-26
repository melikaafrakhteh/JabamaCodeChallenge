package com.jabama.search

import com.jabama.model.RepositoryResponse

data class SearchState(
    val apiIsLoading: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String? = null,
    val searchedRepository: RepositoryResponse? = null
)

sealed interface SearchEvent {
    data class SearchQueryChanged(val query: String) : SearchEvent
    data object Logout : SearchEvent
    data object OnRetryClick : SearchEvent
    data object OnClearQueryClick : SearchEvent
}

sealed interface SearchEffect {
    data object NavigateToLogin : SearchEffect
}