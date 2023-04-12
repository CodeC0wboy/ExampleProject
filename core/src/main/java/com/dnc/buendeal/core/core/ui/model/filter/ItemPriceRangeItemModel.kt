package com.dnc.buendeal.core.core.ui.model.filter

import com.dnc.buendeal.core.base.recycler.ItemModel

data class ItemPriceRangeItemModel(
    val id: Long,
    val priceRange: PriceRangeItemModel,

    var appliedPriceRange: AppliedRange? = null
) : ItemModel

data class AppliedRange(
    var firstValue: String,
    var secondValue: String
)
