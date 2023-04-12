package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class CSRFResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?
)
