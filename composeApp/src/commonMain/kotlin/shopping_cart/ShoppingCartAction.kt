package shopping_cart

sealed class ShoppingCartAction {
    data class IncreaseQuantity(val id: Int) : ShoppingCartAction()
    data class DecreaseQuantity(val id: Int) : ShoppingCartAction()
    data class ToggleChecked(val id: Int, val isChecked: Boolean) : ShoppingCartAction()
}