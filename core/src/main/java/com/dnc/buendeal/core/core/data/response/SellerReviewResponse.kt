package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class SellerReviewResponse(
    @SerializedName("author")
    val author: String?,
    @SerializedName("ctime")
    val ctime: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("media")
    val media: List<String>?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("text")
    val text: String?
)
