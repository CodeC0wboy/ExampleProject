package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class CsrfResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?
)
