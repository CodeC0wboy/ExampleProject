package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class MediaDataResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: TypeResponse?,
    @SerializedName("attributes")
    val attributes: MediaAttributesResponse?
)
