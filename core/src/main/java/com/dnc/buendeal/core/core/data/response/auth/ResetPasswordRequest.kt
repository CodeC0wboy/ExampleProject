package com.dnc.buendeal.core.core.data.response.auth

class ResetPasswordRequest(
    val token: String,
    val password: String,
    val password_confirmation: String,
)
