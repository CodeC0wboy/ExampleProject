package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("data")
    val data: List<CatalogDataResponse>?
)
