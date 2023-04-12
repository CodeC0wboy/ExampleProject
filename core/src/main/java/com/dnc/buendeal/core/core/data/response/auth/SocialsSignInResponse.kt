package com.dnc.buendeal.core.core.data.response.auth

import com.dnc.buendeal.core.core.domain.model.SocialsSignIn
import com.google.gson.annotations.SerializedName

data class SocialsSignInResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("expiresInMinutes")
    val expiresInMinutes: Int,
    @SerializedName("tokenType")
    val tokenType: String,
    @SerializedName("user")
    val user: UserSignInDto
)

fun SocialsSignInResponse.domain(): SocialsSignIn = SocialsSignIn(
    accessToken = accessToken,
    expiresInMinutes = expiresInMinutes,
    tokenType = tokenType,
    user = user.domain()
)
