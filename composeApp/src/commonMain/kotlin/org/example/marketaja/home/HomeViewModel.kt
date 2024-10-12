package org.example.marketaja.home

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.CategoryRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModelState<HomeState, HomeAction>(HomeState()) {

    override fun sendAction(action: HomeAction) {
        when(action) {
            is HomeAction.GetCategory -> getCategory()
        }
    }

    init {
        sendAction(HomeAction.GetCategory)
    }

    private fun getCategory() = viewModelScope.launch {
        categoryRepository.getCategory().stateIn(this)
            .collectLatest {
                update {
                    copy(
                        categoryResponseAsync = it
                    )
                }
            }
    }
}