package org.example.marketaja.home

sealed class HomeAction {
    data object GetUser: HomeAction()

    data object GetUserFailure: HomeAction()
}