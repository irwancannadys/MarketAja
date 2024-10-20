package org.example.marketaja.detail_product

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.product.ProductViewModel

object DetailProductModule {
    fun init() = with(InstancesManager) {
        install {
            DetailProductViewModel(it.get())
        }
    }
}