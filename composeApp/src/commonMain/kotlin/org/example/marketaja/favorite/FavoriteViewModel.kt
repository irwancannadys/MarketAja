package org.example.marketaja.favorite

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository
) : ViewModelState<FavoriteState, FavoriteAction>(FavoriteState()) {

    override fun sendAction(action: FavoriteAction) {
        when (action) {
            is FavoriteAction.GetFavorite -> {
                getFavorite()
            }
        }
    }

    private fun getFavorite() = viewModelScope.launch {
        favoriteRepository
            .getFavorite()
            .stateIn(this)
            .collectLatest { data ->
                update {
                    copy(
                        listOfFavorite = data
                    )
                }
            }
    }
}