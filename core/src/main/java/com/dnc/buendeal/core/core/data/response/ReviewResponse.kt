package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("date") val date: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("photos") val photos: List<Image>? = null,
    @SerializedName("product") val product: Product? = null,
)
