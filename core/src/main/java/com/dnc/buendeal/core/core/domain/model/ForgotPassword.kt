package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.auth.ForgotPasswordResponse

class ForgotPassword(
    val message: String
)

fun ForgotPasswordResponse.domain() = ForgotPassword(message = message)
