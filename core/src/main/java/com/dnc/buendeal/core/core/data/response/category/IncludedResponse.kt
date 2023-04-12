package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class IncludedResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: TypeResponse?,
    @SerializedName("attributes")
    val attributes: IncludedAttributesResponse?,
    @SerializedName("links")
    val links: DataLinksResponse? = null,
    @SerializedName("relationships")
    val relationships: IncludedRelationships? = null
)
