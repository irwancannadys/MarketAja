package org.example.marketaja.favorite

import com.example.data.response.ProductListResponse
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteState(
    val listOfFavorite : List<ProductListResponse.Data> = emptyList()
)