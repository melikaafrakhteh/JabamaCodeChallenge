package com.jabama.challenge.authentication

data class AuthenticationState(
    val isAuthenticated: Boolean = false,
)

sealed interface AuthenticationEvent {
    data object CheckAuthStatus : AuthenticationEvent
}