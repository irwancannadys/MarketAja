package shopping_cart

import com.example.core.service.NetworkAsyncState
import com.example.data.response.ProductListResponse

data class ShoppingCartState(
    val shoppingCartResponseAsync: NetworkAsyncState<List<ProductListResponse.Data>?> = NetworkAsyncState.Idle
)