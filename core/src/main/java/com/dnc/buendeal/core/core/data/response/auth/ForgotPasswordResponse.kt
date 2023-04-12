package com.dnc.buendeal.core.core.data.response.auth

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("message")
    val message: String
)
