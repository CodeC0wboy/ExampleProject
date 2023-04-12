package com.dnc.buendeal.core.core.data.response.filter

import com.google.gson.annotations.SerializedName

data class FilterInnerOptionsResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("label") val label: String?
)
