package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class PricesResponse(
    @SerializedName("actual")
    val actual: String,
    @SerializedName("cent")
    val cent: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("old")
    val old: String,
    @SerializedName("rebate")
    val rebate: String
)
