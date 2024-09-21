package org.example.marketaja.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface AppScreenNavigator {

    fun pop()

    fun navigateToHome()

    fun navigateToFavorite()

}

val LocalAppNavigator = staticCompositionLocalOf<AppScreenNavigator> { error("app navigator not provided!") }
