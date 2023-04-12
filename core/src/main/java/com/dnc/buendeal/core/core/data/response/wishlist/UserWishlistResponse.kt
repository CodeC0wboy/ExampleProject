package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class UserWishlistResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("previewImg")
    val previewImg: String,
    @SerializedName("prices")
    val prices: PricesResponse,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("votes")
    val votes: Int
)
