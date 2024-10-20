package org.example.marketaja.detail_product

import org.example.marketaja.product.ProductAction

sealed class DetailProductAction {
    data class setDetailProductId(val id: Int) : DetailProductAction()
    data object GetDetailProduct: DetailProductAction()
}