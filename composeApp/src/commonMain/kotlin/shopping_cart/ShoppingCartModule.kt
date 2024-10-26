package shopping_cart

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.product.ProductViewModel

object ShoppingCartModule {
    fun init() = with(InstancesManager) {
        install {
            ShoppingCartViewModel()
        }
    }
}