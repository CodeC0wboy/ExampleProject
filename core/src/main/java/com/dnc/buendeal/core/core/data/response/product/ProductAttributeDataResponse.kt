package com.dnc.buendeal.core.core.data.response.product

import androidx.annotation.Keep

@Keep
data class ProductAttributeDataResponse(
    val id: String?,
    val type: String?,
    val attributes: ProductAttributeAttributesResponse?
)
