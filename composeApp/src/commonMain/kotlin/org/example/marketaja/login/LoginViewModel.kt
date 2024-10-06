package org.example.marketaja.login

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModelState<LoginState, LoginAction>(LoginState()) {

    override fun sendAction(action: LoginAction) {
        when (action) {
            is LoginAction.setUsername -> {
                handleSetUserName(action.userName)
            }
            is LoginAction.setPassword -> {
                handlePassword(action.password)
            }
            is LoginAction.login -> login()
        }
    }

    private fun handleSetUserName(username: String) {
        update {
            copy(
                username = username
            )
        }
    }

    private fun handlePassword(password: String) {
        update {
            copy(
                password = password
            )
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