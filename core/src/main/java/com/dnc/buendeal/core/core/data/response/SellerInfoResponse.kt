package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class SellerInfoResponse(
    @SerializedName("address")
    val address: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("media_id")
    val mediaId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("positiveReviews")
    val positiveReviews: Int?,
    @SerializedName("productsCount")
    val productsCount: Int?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("reviews")
    val reviews: Int?,
    @SerializedName("reviewsCount")
    val reviewsCount: Int?,
    @SerializedName("siteid")
    val siteid: String?,
    @SerializedName("url")
    val url: String?
)
