package org.example.marketaja.detail_product

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.DetailProductRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val detailProductRepository: DetailProductRepository
) :
    ViewModelState<DetailProductState, DetailProductAction>(DetailProductState()) {

    override fun sendAction(action: DetailProductAction) {
        when (action) {
            is DetailProductAction.setDetailProductId -> {
                update {
                    copy(productId = action.id)
                }
            }
            is DetailProductAction.GetDetailProduct -> {
                getDetailProduct()
            }
        }
    }

    private fun getDetailProduct() = viewModelScope.launch {
        val productId = currentState().productId
        detailProductRepository
            .getDetailProduct(productId)
            .stateIn(this)
            .collectLatest {
                update {
                    copy(
                        detailResponseAsync = it
                    )
                }
            }
    }
}