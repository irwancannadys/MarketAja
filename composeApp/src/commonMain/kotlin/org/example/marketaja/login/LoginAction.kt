package org.example.marketaja.login

sealed class LoginAction {
    data class setUsername(val userName: String): LoginAction()
    data class setPassword(val password: String): LoginAction()
    data object login: LoginAction()
}