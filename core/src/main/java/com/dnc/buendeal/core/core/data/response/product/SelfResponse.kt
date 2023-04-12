package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class SelfResponse(
    @SerializedName("href") val href: String?,
    @SerializedName("allow") val allow: List<String>?
)
