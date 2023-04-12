package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductTextResponse(
    @SerializedName("data") val data: List<ProductTextDataResponse>
)
