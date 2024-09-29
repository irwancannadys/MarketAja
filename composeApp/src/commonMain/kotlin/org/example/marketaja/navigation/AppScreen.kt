package org.example.marketaja.navigation

import com.slack.circuit.runtime.screen.Screen
import org.example.marketaja.Parcelable
import org.example.marketaja.Parcelize

@Parcelize
sealed class AppScreen : Screen, Parcelable {

    @Parcelize
    data object Login: AppScreen(), Parcelable

    @Parcelize
    data object Home : AppScreen(), Parcelable

    @Parcelize
    data object Favorite: AppScreen(), Parcelable

    @Parcelize
    data class ProductDetail(
        val nameProduct: String,
    ) : AppScreen(), Parcelable

}