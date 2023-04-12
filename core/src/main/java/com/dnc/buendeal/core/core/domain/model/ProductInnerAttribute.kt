package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.ProductInnerAttributeDto
import com.dnc.buendeal.core.core.ui.model.ProductInnerAttributeItemModel

data class ProductInnerAttribute(
    val attrName: String,
    val attrValue: String?,
)

fun ProductInnerAttributeDto.domain(): ProductInnerAttribute = ProductInnerAttribute(
    attrName = attrName,
    attrValue = attrValue
)

fun ProductInnerAttributeItemModel.domain() = ProductInnerAttribute(
    attrName = attrName,
    attrValue = attrValue
)
