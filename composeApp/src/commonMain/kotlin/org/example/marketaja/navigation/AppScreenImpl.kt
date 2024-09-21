package org.example.marketaja.navigation

import com.slack.circuit.runtime.Navigator

class AppScreenImpl(
    private val navigator: Navigator

) : AppScreenNavigator {
    override fun pop() {
        navigator.pop()
    }

    override fun navigateToHome() {
        navigator.resetRoot(AppScreen.Home)
    }

    override fun navigateToFavorite() {
        navigator.goTo(AppScreen.Favorite)
    }
}