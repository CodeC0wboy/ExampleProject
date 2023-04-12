package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class DataRelationshipsResponse(
    @SerializedName("catalog")
    val catalog: CatalogResponse?,
    @SerializedName("media")
    val media: CategoryMediaResponse?
)
