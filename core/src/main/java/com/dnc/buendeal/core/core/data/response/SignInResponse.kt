package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("expiresInMinutes")
    val expiresInMinutes: Int?,
    @SerializedName("tokenType")
    val tokenType: String?,
    @SerializedName("user")
    val user: UserResponse?
)
