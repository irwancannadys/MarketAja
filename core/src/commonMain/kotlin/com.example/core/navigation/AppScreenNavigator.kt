package com.example.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface AppScreenNavigator {

    // ini untuk back
    fun pop()

    fun navigateToHome()

    fun navigateToFavorite()

    fun navigateToProductDetail(id: Int)

    fun navigateToProductList(id: Int, name: String)

}

val LocalAppNavigator = staticCompositionLocalOf<AppScreenNavigator> { error("app navigator not provided!") }