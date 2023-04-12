package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.ProductMainAttributeDto

data class ProductMainAttribute(
    val label: String,
    val innerAttributes: List<ProductInnerAttribute>
)

fun ProductMainAttributeDto.domain(): ProductMainAttribute = ProductMainAttribute(
    label = label,
    innerAttributes = innerAttributes.map { it.domain() }
)
