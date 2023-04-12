package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.OptionColorsResponse

data class OptionColors(
    val id: Long,
    val colors: List<Color>
)

fun OptionColorsResponse.domain(): OptionColors = OptionColors(
    id = id,
    colors = colors.map { it.domain() }
)
