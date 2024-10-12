package org.example.marketaja.product

import com.example.core.service.NetworkAsyncState
import com.example.data.response.CategoryResponse
import com.example.data.response.FavoriteResponse
import com.example.data.response.ProductListResponse

data class ProductState(
    val categoryId: Int = 0,
    val favorite: FavoriteResponse? = null,
    val productResponseAsync: NetworkAsyncState<List<ProductListResponse.Data>> = NetworkAsyncState.Idle
)