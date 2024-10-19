package org.example.marketaja.product

import androidx.lifecycle.viewModelScope
import com.example.core.service.NetworkAsyncState
import com.example.core.service.getOrNull
import com.example.core.viewmodel.ViewModelState
import com.example.data.repository.FavoriteRepository
import com.example.data.repository.ProductListRepository
import com.example.data.response.FavoriteResponse
import com.example.data.response.ProductListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProductViewModel(
    private val productListRepository: ProductListRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModelState<ProductState, ProductAction>(ProductState()) {

    private var favoriteResponse = mutableListOf<FavoriteResponse>()
    private val _messageErrorAddFavorite = MutableStateFlow(false)

    override fun sendAction(action: ProductAction) {
        when (action) {
            is ProductAction.SetCategoryId -> {
                update {
                    copy(categoryId = action.categoryId)
                }
            }

            is ProductAction.SetFavorite -> {
                update {
                    copy(
                        favorite = action.favorite
                    )
                }
                addFavorite()
            }

            is ProductAction.SetFavoriteTextButton -> {
                updateTextButtonFavorite(action.isFavorite)
            }

            is ProductAction.RemoveFavorite -> {
                removeFavoriteById(action.id)
            }

            is ProductAction.GetProductList -> {
                getProductList()
            }
        }
    }

    private fun updateTextButtonFavorite(isFavorite: Boolean) {
        update {
            copy(
                favoriteTextButton = isFavorite
            )
        }

    }

    private fun addFavorite() = viewModelScope.launch {
        val populateData  = currentState().favorite?.toFavoriteResponse()
        println("datanya $populateData")

        if (populateData != null) {
            val alreadyExists = favoriteResponse.any { it.id == populateData.id }
            if (!alreadyExists) {
                favoriteResponse.add(populateData)
                updateProductFavoriteStatus(populateData.id, true)
            } else {
                println("Item with id ${populateData.id} already exists in favorites.")
            }
        }
        val jsonFavorite = Json.encodeToString(favoriteResponse)
        println("List dalam bentuk JSON: $jsonFavorite")
        favoriteRepository
            .addToFavorite(jsonFavorite)
            .stateIn(this)
            .collectLatest {
                update {
                    copy()
                }
            }
    }

    private fun removeFavoriteById(id: Int) = viewModelScope.launch {
        favoriteResponse = favoriteResponse.filter { it.id != id }.toMutableList()
        updateProductFavoriteStatus(id, false)
        val jsonFavorite = Json.encodeToString(favoriteResponse)
        println("List dalam bentuk JSON: $jsonFavorite")

        favoriteRepository
            .addToFavorite("")
            .stateIn(this)
            .collectLatest {
                update {
                    copy()
                }
            }
    }

    private fun updateProductFavoriteStatus(id: Int, isFavorite: Boolean) {
        val updatedProducts = currentState().productResponseAsync.getOrNull()?.map { product ->
            if (product.id == id) {
                product.copy(isFavorite = isFavorite)
            } else {
                product
            }
        }
        update {
            copy(productResponseAsync = NetworkAsyncState.Success(updatedProducts))
        }
    }

    private fun ProductListResponse.Data.toFavoriteResponse(): FavoriteResponse {
        return FavoriteResponse(
            id = this.id,
            name = this.name,
            price = this.price,
            images = this.images
        )
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