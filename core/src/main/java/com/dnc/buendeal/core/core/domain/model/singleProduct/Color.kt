package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.ColorResponse
import com.dnc.buendeal.core.core.ui.model.ColorItemModel

data class Color(
    val id: Long,
    val name: String,
    val color: String,
    val idProduct: String
)

fun ColorResponse.domain(): Color = Color(
    id = id,
    name = name,
    color = color,
    idProduct = idProduct
)

fun ColorItemModel.domain() = Color(
    id = id,
    name = name,
    color = color,
    idProduct = idProduct
)
