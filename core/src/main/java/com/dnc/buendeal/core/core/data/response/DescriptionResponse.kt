package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class DescriptionResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("description") val description: String
)
