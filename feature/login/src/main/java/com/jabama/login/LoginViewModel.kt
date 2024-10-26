package com.jabama.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.common.Resource
import com.jabama.domain.usecase.aouth.GetAccessTokenUseCase
import com.jabama.domain.usecase.token.SaveTokenUseCase
import com.jabama.feature.login.BuildConfig
import com.jabama.model.RequestAccessToken
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val accessTokenUseCase: GetAccessTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.AuthorizeClicked -> authorize()
        }
    }

    private fun getAuthorizationUrl(): String {
        return "https://github.com/login/oauth/authorize?client_id=${BuildConfig.GITHUB_CLIENT_ID}&redirect_uri=${BuildConfig.GITHUB_CALLBACK_URL}&scope=repo user&state=0"
    }

    private fun authorize() {
        Uri.parse(getAuthorizationUrl()).getQueryParameter(CODE_KEY)?.let { code ->
            getToken(code)
        }
    }

    private fun getToken(code: String) {
        viewModelScope.launch {

            _state.update { it.copy(authorizeButtonIsLoading = true) }

            accessTokenUseCase(
                RequestAccessToken(
                    clientId = BuildConfig.GITHUB_CLIENT_ID,
                    clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
                    code = code,
                    redirectUri = BuildConfig.GITHUB_CALLBACK_URL,
                    state = "0"
                )
            ).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                authorizeButtonIsLoading = false,
                                isFailed = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                authorizeButtonIsLoading = false,
                                isFailed = false
                            )
                        }

                        saveToken(result.data.accessToken)

                        navigateToSearch()

                    }
                }
            }
        }
    }

    private suspend fun navigateToSearch() {
        _effect.send(LoginEffect.NavigateToSearch)
    }

    private fun saveToken(token: String) {
        saveTokenUseCase.invoke(token)
    }

    companion object {
        private const val CODE_KEY = "code"
    }
}
