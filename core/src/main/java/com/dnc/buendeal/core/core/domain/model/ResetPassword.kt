package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.auth.ResetPasswordResponse

data class ResetPassword(
    val message: String
)

fun ResetPasswordResponse.domain() = ResetPassword(message = message)
