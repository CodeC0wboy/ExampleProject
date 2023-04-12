package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductMediaResponse(
    @SerializedName("data") val data: List<ProductMediaDataResponse>?
)
