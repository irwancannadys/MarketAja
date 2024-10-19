package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val images: String
)
