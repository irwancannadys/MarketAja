package org.example.marketaja.product

import com.example.data.response.ProductListResponse

sealed class ProductAction {
    data class SetCategoryId(val categoryId: Int): ProductAction()
    data class SetFavorite(val favorite: ProductListResponse.Data): ProductAction()
    data class RemoveFavorite(val id: Int) : ProductAction()
    data class SetFavoriteTextButton(val isFavorite: Boolean) : ProductAction()
    data object GetProductList: ProductAction()
}