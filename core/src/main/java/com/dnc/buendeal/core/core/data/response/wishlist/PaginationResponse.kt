package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("last")
    val last: Int,
    @SerializedName("next")
    val next: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("prev")
    val prev: Int
)
