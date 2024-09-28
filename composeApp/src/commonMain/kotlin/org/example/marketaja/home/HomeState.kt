package org.example.marketaja.home

import org.example.marketaja.data.response.UserResponse
import org.example.marketaja.service.NetworkAsyncState

data class HomeState(
    val asyncUserResponse: NetworkAsyncState<UserResponse> = NetworkAsyncState.Idle
)
