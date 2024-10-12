package org.example.marketaja.home

sealed class HomeAction {
    data object GetCategory: HomeAction()
}