package com.example.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailProductResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null,
    @SerialName("data")
    val data: Data? = null
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("price")
        val price: Double?,
        @SerialName("images")
        val images: List<String>
    )
}
