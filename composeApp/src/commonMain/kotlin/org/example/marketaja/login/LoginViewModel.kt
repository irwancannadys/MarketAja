package org.example.marketaja.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.marketaja.ViewModelState
import org.example.marketaja.data.repository.LoginRepository

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModelState<LoginState, LoginAction>(LoginState()) {

    override fun sendAction(action: LoginAction) {
        when (action) {
            is LoginAction.setUsername -> {
                handleSetUserName()
            }
            is LoginAction.setPassword -> {}
            is LoginAction.login -> login()
        }
    }

    private fun handleSetUserName() {
        if (currentState().username.length <= 3) {

        }
    }

    private fun login() = viewModelScope.launch(Dispatchers.IO) {
        loginRepository.login(
            username = currentState().username,
            password = currentState().password
        ).stateIn(this)
            .collectLatest {
                update {
                    copy(
                        asyncLogin = it
                    )
                }
            }
    }

}