package org.example.marketaja.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface AppScreenNavigator {

    // ini untuk back
    fun pop()

    fun navigateToHome()

    fun navigateToFavorite()

    fun navigateToProductDetail(name: String)

}

val LocalAppNavigator = staticCompositionLocalOf<AppScreenNavigator> { error("app navigator not provided!") }
