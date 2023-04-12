package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.FilterSort
import com.dnc.buendeal.core.core.domain.model.SortCriteria

data class FilterSortItemModel(
    val id: Long,
    val criteriaList: List<SortCriteria>
) : ItemModel

fun FilterSort.itemModel() = FilterSortItemModel(
    id = id,
    criteriaList = criteriaList
)
