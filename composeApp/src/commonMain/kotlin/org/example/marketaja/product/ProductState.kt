package org.example.marketaja.product

import com.example.core.service.NetworkAsyncState
import com.example.data.response.ProductListResponse

data class ProductState(
    val categoryId: Int = 0,
    val favorite: ProductListResponse.Data? = null,
    val favoriteTextButton : Boolean = false,
    val productResponseAsync: NetworkAsyncState<List<ProductListResponse.Data>?> = NetworkAsyncState.Idle
)