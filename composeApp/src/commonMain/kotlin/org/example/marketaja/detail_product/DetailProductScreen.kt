package org.example.marketaja.detail_product

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DetailProductScreen(
    productName: String
) {
    Scaffold {
        Text("Product name =  $productName")
    }
}