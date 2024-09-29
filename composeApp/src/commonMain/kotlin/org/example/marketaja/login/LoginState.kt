package org.example.marketaja.login

import androidx.compose.runtime.Immutable
import org.example.marketaja.service.NetworkAsyncState

@Immutable
data class LoginState(
    val username: String = "",
    val password: String = "",
    val asyncLogin: NetworkAsyncState<String> = NetworkAsyncState.Idle
)