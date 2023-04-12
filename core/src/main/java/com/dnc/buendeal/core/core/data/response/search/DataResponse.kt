package com.dnc.buendeal.core.core.data.response.search

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("catalogItems")
    val catalogItems: List<CatalogItemResponse>,
    @SerializedName("items")
    val items: List<ItemResponse>,
    @SerializedName("search")
    val search: String,
    @SerializedName("type")
    val type: String
)
