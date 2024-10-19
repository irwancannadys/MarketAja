package org.example.marketaja.favorite

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.product.ProductViewModel

object FavoriteModule {

    fun init() = with(InstancesManager) {
        install {
            FavoriteViewModel(it.get())
        }
    }
}