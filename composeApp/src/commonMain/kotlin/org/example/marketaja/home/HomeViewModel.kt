package org.example.marketaja.home

import com.example.core.viewmodel.ViewModelState

class HomeViewModel : ViewModelState<HomeState, HomeAction>(HomeState()) {

//    private val networkBuilder = NetworkBuilder()
//    private val remoteDataSource = RepositoryImpl(networkBuilder)

    override fun sendAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetUser -> {}
            is HomeAction.GetUserFailure -> {}
        }
    }

//    fun getUser() = viewModelScope.launch {
//        remoteDataSource.getUser()
//            .collectLatest { async ->
//                update {
//                    copy(asyncUserResponse = async)
//                }
//            }
//    }
}