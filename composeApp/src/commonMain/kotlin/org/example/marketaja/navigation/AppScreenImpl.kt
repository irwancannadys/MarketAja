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

    override fun navigateToProductDetail(id: Int) {
        navigator.goTo(AppScreen.ProductDetail(id))
    }

    override fun navigateToProductList(id: Int, name: String) {
        navigator.goTo(AppScreen.ProductList(id, name))
    }
}