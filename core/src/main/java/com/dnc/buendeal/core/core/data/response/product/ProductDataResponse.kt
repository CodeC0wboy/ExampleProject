package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductDataResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("links") val links: ProductDataLinksResponse?,
    @SerializedName("attributes") val attributes: ProductAttributesResponse?,
    @SerializedName("relationships") val relationships: RelationshipsResponse?
)
