package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class IncludedProductRelationships(
    @SerializedName("media") val media: ProductMediaResponse,
    @SerializedName("price") val price: ProductPriceResponse,
    @SerializedName("attribute") val attribute: ProductAttributeResponse?,
)
