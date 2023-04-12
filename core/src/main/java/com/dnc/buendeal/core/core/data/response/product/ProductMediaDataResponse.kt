package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductMediaDataResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("attributes") val attributes: ProductMediaAttributesResponse?
)
