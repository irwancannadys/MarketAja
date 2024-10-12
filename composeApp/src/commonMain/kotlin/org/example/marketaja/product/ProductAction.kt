package org.example.marketaja.product

import com.example.data.response.FavoriteResponse
import org.example.marketaja.home.HomeAction

sealed class ProductAction {
    data class SetCategoryId(val categoryId: Int): ProductAction()
    data class SetFavorite(val favorite: FavoriteResponse): ProductAction()
    data object GetProductList: ProductAction()

}