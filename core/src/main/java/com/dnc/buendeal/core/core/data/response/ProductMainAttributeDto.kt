package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class ProductMainAttributeDto(
    @SerializedName("label") val label: String,
    @SerializedName("innerAttributes") val innerAttributes: List<ProductInnerAttributeDto>
)
