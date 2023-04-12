package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.LogoutResponse

data class Logout(
    val message: String?
)

fun LogoutResponse.domain() = Logout(message = message)
