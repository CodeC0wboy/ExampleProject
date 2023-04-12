package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

enum class TypeResponse(val value: String) {
    @SerializedName("catalog")
    Catalog("catalog"),

    @SerializedName("media")
    Media("media")
}
