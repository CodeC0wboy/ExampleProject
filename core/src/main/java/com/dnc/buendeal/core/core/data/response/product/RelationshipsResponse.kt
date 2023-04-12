package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class RelationshipsResponse(
    @SerializedName("media") val media: ProductMediaResponse?,
    @SerializedName("price") val price: ProductPriceResponse?,
    @SerializedName("attribute") val attribute: ProductAttributeResponse?,
    @SerializedName("text") val text: ProductTextResponse?,
    @SerializedName("product") val product: ProductProductResponse?,
)
