package org.example.marketaja.home

import org.example.marketaja.data.response.UserResponse
import com.example.core.service.NetworkAsyncState

data class HomeState(
    val asyncUserResponse: NetworkAsyncState<UserResponse> = NetworkAsyncState.Idle
)
