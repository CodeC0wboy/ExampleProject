package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class ProductInnerAttributeDto(
    @SerializedName("attrName") val attrName: String,
    @SerializedName("attrValue") val attrValue: String? = null,
)
