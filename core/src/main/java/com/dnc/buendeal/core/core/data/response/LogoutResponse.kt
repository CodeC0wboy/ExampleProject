package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("message")
    val message: String?
)
