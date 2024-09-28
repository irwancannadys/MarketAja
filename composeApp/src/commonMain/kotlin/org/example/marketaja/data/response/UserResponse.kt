package org.example.marketaja.data.response

import kotlinx.serialization.SerialName

data class UserResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null,
    @SerialName("data")
    val `data`: Data? = null
)

data class Data(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)
