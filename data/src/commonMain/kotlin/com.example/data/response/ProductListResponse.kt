package com.example.data.response

import com.example.data.response.CategoryResponse.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null,
    @SerialName("data")
    val data: List<Data>? = null
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("sort_description")
        val sortDescription: String = "",  // Default value
        @SerialName("category")
        val category: Category = Category(0, "", ""),  // Default value
        @SerialName("price")
        val price: Double,
        @SerialName("rating")
        val rating: Double = 0.0,  // Default value
        @SerialName("discount")
        val discount: Int = 0,  // Default value
        @SerialName("images")
        val images: String,
        @SerialName("isFavorite")
        val isFavorite: Boolean = false
    ) {
        @Serializable
        data class Category(
            @SerialName("id")
            val id: Int,
            @SerialName("name")
            val name: String,
            @SerialName("description")
            val description: String
        )
    }
}
