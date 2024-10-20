package org.example.marketaja.detail_product

import com.example.core.service.NetworkAsyncState
import com.example.data.response.DetailProductResponse

data class DetailProductState(
    val productId: Int = 0,
    val detailResponseAsync: NetworkAsyncState<DetailProductResponse.Data?> = NetworkAsyncState.Idle
)