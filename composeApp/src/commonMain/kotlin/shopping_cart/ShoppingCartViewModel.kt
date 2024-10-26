package shopping_cart

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.ViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CartResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    var check: Boolean = true
)


//data class CartResponseMapper(
//    val id: Int,
//    val name: String,
//    val price: Double,
//    val quantity: Int,
//)

class ShoppingCartViewModel : ViewModelState<ShoppingCartState, ShoppingCartAction>(
    ShoppingCartState()
) {

    override fun sendAction(action: ShoppingCartAction) {
        when (action) {
            is ShoppingCartAction.IncreaseQuantity -> {
                increaseQuantity(action.id)
            }

            is ShoppingCartAction.DecreaseQuantity -> {
                decreaseQuantity(action.id)
            }

            is ShoppingCartAction.ToggleChecked -> {
                toggleItemChecked(action.id, action.isChecked)
            }
        }
    }

    private val _shoppingCartData = MutableStateFlow(
        listOf(
            CartResponse(
                id = 1,
                name = "Celana Erigo",
                price = 150000.0,
                quantity = 1,
                check = true
            ),
            CartResponse(
                id = 2,
                name = "Baju Erigo",
                price = 100000.0,
                quantity = 1,
                check = true
            ),
            CartResponse(
                id = 3,
                name = "Rok Erigo",
                price = 70000.0,
                quantity = 1,
                check = true
            ),
            CartResponse(
                id = 4,
                name = "Tas Erigo",
                price = 250000.0,
                quantity = 1,
                check = true
            )
        )
    )
    val shoppingCartData = _shoppingCartData.asStateFlow()

    private val _totalPrice = MutableStateFlow(calculateTotalPrice(_shoppingCartData.value))
    val totalPrice = _totalPrice.asStateFlow()

    private fun calculateTotalPrice(items: List<CartResponse>): Double {
        return items.filter {
            it.check
        }.sumOf {
            it.price * it.quantity
        }
    }

    private fun increaseQuantity(idProduct: Int) {
        viewModelScope.launch {
            _shoppingCartData.update { currentValue ->
                currentValue.map { data ->
                    if (data.id == idProduct) data.copy(quantity = data.quantity + 1) else data
                }
            }
            _totalPrice.value = calculateTotalPrice(_shoppingCartData.value)
        }
    }

    private fun decreaseQuantity(idProduct: Int) {
        viewModelScope.launch {
            _shoppingCartData.update { currentValue ->
                currentValue.map { data ->
                    if (data.id == idProduct && data.quantity > 1) {
                        data.copy(quantity = data.quantity - 1)
                    } else data
                }
            }
            _totalPrice.value = calculateTotalPrice(_shoppingCartData.value)
        }
    }

    private fun toggleItemChecked(itemId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            _shoppingCartData.update { currentValue ->
                currentValue.map { data ->
                    if (data.id == itemId){
                        data.copy(check = isChecked)
                    } else data
                }
            }
            _totalPrice.value = calculateTotalPrice(_shoppingCartData.value)
        }
    }

//    private fun CartResponse.mapperToLocalData(): CartResponseMapper {
//        return CartResponseMapper(
//            id = this.id,
//            name= this.name,
//            price= this.price,
//            quantity= this.quantity
//        )
//    }

}