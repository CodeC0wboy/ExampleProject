package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class CategoryMediaResponse(
    @SerializedName("data")
    val data: List<MediaDataResponse>?
)
