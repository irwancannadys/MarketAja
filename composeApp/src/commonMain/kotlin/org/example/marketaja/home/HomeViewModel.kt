package org.example.marketaja.home

import org.example.marketaja.ViewModelState

class HomeViewModel : ViewModelState<HomeState, HomeAction>(HomeState()) {

//    private val networkBuilder = NetworkBuilder()
//    private val remoteDataSource = RepositoryImpl(networkBuilder)

    override fun handleAction(action: HomeAction) {
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