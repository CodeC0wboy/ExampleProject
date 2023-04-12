package com.dnc.buendeal.core.core.data.response.search

import com.google.gson.annotations.SerializedName

data class CatalogItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("url")
    val url: String
)
