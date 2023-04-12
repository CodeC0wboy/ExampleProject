package com.dnc.buendeal.core.core.data.response.product

import com.dnc.buendeal.core.core.data.response.LinksResponse
import com.dnc.buendeal.core.core.data.response.MetaResponse
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("meta") val meta: MetaResponse?,
    @SerializedName("links") val links: LinksResponse?,
    @SerializedName("data") val data: ProductDataResponse?,
    @SerializedName("included") val included: List<ProductIncludedResponse>?
)
