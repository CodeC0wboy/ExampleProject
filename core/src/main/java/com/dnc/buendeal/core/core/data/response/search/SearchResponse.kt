package com.dnc.buendeal.core.core.data.response.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: DataResponse
)
