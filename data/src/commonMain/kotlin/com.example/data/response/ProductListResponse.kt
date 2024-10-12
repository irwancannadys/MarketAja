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
        val sortDescription: String,
        @SerialName("category")
        val category: Category,
        @SerialName("price")
        val price: Double,
        @SerialName("rating")
        val rating: Double,
        @SerialName("discount")
        val discount: Int,
        @SerialName("images")
        val images: String
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
