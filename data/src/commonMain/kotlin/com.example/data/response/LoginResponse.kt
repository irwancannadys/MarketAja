package org.example.marketaja.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null,
    @SerialName("data")
    val data: Data? = null
) {
    @Serializable
    data class Data(
        @SerialName("token")
        val token: String?
    )
}
