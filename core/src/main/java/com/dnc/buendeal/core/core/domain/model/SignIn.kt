package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.SignInResponse

data class SignIn(
    val accessToken: String?,
    val expiresInMinutes: Int?,
    val tokenType: String?,
    val user: User?
)

fun SignInResponse.domain() = SignIn(
    accessToken = accessToken,
    expiresInMinutes = expiresInMinutes,
    tokenType = tokenType,
    user = user?.domain()
)
