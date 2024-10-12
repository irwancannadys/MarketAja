package org.example.marketaja.home

import org.example.marketaja.data.response.UserResponse
import com.example.core.service.NetworkAsyncState
import com.example.data.response.CategoryResponse

data class HomeState(
    val categoryResponseAsync: NetworkAsyncState<List<CategoryResponse.Data>> = NetworkAsyncState.Idle
)
