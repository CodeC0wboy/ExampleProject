package com.dnc.buendeal.core.core.data.response.filter

import com.google.gson.annotations.SerializedName

data class FilterResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("items") val items: List<FilterInnerOptionsResponse>?,
)
