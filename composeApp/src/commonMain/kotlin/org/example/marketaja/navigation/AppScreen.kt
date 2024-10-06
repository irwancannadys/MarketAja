package org.example.marketaja.navigation

import com.example.core.Parcelable
import com.example.core.Parcelize
import com.slack.circuit.runtime.screen.Screen

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