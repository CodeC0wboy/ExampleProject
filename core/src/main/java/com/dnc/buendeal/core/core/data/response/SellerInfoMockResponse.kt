package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.BuildConfig
import com.dnc.buendeal.core.core.domain.data.Image
import com.google.gson.annotations.SerializedName

class SellerInfoMockResponse(
    @SerializedName("imgRes") val imgRes: Image,
    @SerializedName("name") val name: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("votes") val votes: Int? = null,
    @SerializedName("daysOnPlatform") val daysOnPlatform: Int? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("about") val about: String? = null,
    @SerializedName("siteId") val siteId: String = "1.3.",
    @SerializedName("url") val url: String = BuildConfig.BASE_URL
)
