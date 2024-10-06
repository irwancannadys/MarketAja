package org.example.marketaja.login

import androidx.compose.runtime.Immutable
import com.example.core.service.NetworkAsyncState

@Immutable
data class LoginState(
    val username: String = "",
    val password: String = "",
    val asyncLogin: NetworkAsyncState<String> = NetworkAsyncState.Idle
)