package org.example.marketaja.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.example.marketaja.ViewModelState
import org.example.marketaja.data.RepositoryImpl
import org.example.marketaja.service.NetworkBuilder

class HomeViewModel : ViewModelState<HomeState, HomeAction>(HomeState()) {

    private val networkBuilder = NetworkBuilder()
    private val remoteDataSource = RepositoryImpl(networkBuilder)

    override fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetUser -> {}
            is HomeAction.GetUserFailure -> {}
        }
    }

    fun getUser() = viewModelScope.launch {
        remoteDataSource.getUser()
            .collectLatest { async ->
                update {
                    copy(asyncUserResponse = async)
                }
            }
    }
}