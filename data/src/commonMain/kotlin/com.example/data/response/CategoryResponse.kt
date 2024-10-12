package com.example.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.marketaja.data.response.LoginResponse.Data

@Serializable
data class CategoryResponse(
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
        val id: Int?,
        @SerialName("name")
        val name: String?,
        @SerialName("description")
        val description: String?
    )
}
