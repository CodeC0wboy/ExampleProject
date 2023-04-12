package com.dnc.buendeal.core.core.ui.model.filter

import com.dnc.buendeal.core.base.recycler.ItemModel

data class ApplyFiltersBlockItemModel(
    val id: Long,
    val appliedFilters: List<FilterItemModel>
) : ItemModel
