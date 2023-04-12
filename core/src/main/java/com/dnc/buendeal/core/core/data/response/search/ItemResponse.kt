package com.dnc.buendeal.core.core.data.response.search

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("previewImg")
    val previewImg: String,
    @SerializedName("prices")
    val prices: PricesResponse,
    @SerializedName("shopName")
    val shopName: String,
    @SerializedName("url")
    val url: String
)
