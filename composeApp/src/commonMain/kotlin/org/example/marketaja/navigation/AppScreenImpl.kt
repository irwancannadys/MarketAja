package org.example.marketaja.navigation

import com.example.core.navigation.AppScreenNavigator
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

    override fun navigateToProductDetail(name: String) {
        navigator.goTo(AppScreen.ProductDetail(name))
    }

    override fun navigateToProductList(id: Int) {
        navigator.goTo(AppScreen.ProductList(id))
    }
}