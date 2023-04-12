package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class BoardResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("likesCount")
    val likesCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("previewItems")
    val previewItems: List<String>,
    @SerializedName("totalProducts")
    val totalProducts: Int
)
