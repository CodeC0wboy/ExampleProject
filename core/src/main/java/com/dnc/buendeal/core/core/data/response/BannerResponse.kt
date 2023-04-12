package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("url")
    val url: String?
)
