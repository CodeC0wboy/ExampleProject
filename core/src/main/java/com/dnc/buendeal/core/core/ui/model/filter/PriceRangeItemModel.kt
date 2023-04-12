package com.dnc.buendeal.core.core.ui.model.filter

import com.dnc.buendeal.core.core.domain.model.filter.PriceRange

data class PriceRangeItemModel(
    val priceRange: List<Long>
)

fun PriceRange.itemModel() = PriceRangeItemModel(
    priceRange = priceRange
)
