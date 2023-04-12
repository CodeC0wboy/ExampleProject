package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class MediaPreviews(
    @SerializedName("240")
    val img240: String,
    @SerializedName("346")
    val img346: String,
    @SerializedName("600")
    val img600: String,
    @SerializedName("720")
    val img720: String,
    @SerializedName("1200")
    val img1200: String
)
