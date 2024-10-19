package org.example.marketaja.favorite

sealed class FavoriteAction {
    data object GetFavorite : FavoriteAction()
}