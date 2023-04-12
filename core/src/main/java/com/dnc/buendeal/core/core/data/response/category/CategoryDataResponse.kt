package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class CategoryDataResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("links")
    val links: DataLinksResponse?,
    @SerializedName("attributes")
    val attributes: DataAttributesResponse?,
    @SerializedName("relationships")
    val relationships: DataRelationshipsResponse?
)
