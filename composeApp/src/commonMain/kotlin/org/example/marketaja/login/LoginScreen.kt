package org.example.marketaja.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.marketaja.di.viewModel
import org.example.marketaja.navigation.LocalAppNavigator
import org.example.marketaja.service.NetworkAsyncState

@Composable
fun LoginScreen() {

    val navigator = LocalAppNavigator.current
    val viewModel by viewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()

    when(state.asyncLogin){
        is NetworkAsyncState.Success -> {
            navigator.navigateToHome()
        }
        is NetworkAsyncState.Failure -> {}
        is NetworkAsyncState.Idle -> {}
        is NetworkAsyncState.Loading -> {
            CircularProgressIndicator()
        }
    }

    Scaffold {
        Column {

            Text("Login")
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                onValueChange = {
                    viewModel.sendAction(LoginAction.setUsername(it))
                },
                value = state.username,
                label = {
                    Text("Username")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                onValueChange = {
                    viewModel.sendAction(LoginAction.setPassword(it))
                },
                value = state.password,
                label = {
                    Text("Password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.sendAction(LoginAction.login)
                }
            ) {
                Text("Login")
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.restartState()
        }
    }
}