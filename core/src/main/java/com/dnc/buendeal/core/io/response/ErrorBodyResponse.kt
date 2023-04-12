package com.dnc.buendeal.core.io.response

import com.google.gson.annotations.SerializedName

data class ErrorBodyResponse(
    @SerializedName("errors")
    val errors: Map<String, Any>?,

    @SerializedName("message")
    val message: String?
)
