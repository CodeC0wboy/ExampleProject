package com.dnc.buendeal.core.core.data.response.product

import com.dnc.buendeal.core.core.data.response.SelfResponse
import com.google.gson.annotations.SerializedName

data class ProductDataLinksResponse(
    @SerializedName("self") val self: SelfResponse?,
    @SerializedName("basket/product") val basketProduct: SelfResponse?
)
