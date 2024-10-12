package org.example.marketaja.product

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.ProductListRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productListRepository: ProductListRepository
) : ViewModelState<ProductState, ProductAction>(ProductState()) {

    override fun sendAction(action: ProductAction) {
        when (action) {
            is ProductAction.SetCategoryId -> {
                update {
                    copy(categoryId = action.categoryId)
                }
            }
            is ProductAction.SetFavorite -> {
                addFavorite()
            }
            is ProductAction.GetProductList -> {
                getProductList()
            }
        }
    }

    private fun addFavorite() {

    }

    private fun getProductList() = viewModelScope.launch {
        productListRepository
            .getProductList(
                categoryId = currentState().categoryId.toString()
            )
            .stateIn(this)
            .collectLatest {
                update {
                    copy(
                        productResponseAsync = it
                    )
                }
            }
    }

}