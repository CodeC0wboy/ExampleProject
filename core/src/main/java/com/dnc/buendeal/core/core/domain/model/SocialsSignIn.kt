package com.dnc.buendeal.core.core.domain.model

data class SocialsSignIn(
    val accessToken: String,
    val expiresInMinutes: Int,
    val tokenType: String,
    val user: UserSignIn
)
