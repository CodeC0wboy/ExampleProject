package com.dnc.buendeal.features.products.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.FilterSort
import com.dnc.buendeal.core.core.domain.model.SortCriteria

data class ProductsFilterSortItemModel(
    val id: Long,
    val criteriaList: List<SortCriteria>
) : ItemModel

fun FilterSort.itemModel(): ProductsFilterSortItemModel = ProductsFilterSortItemModel(
    id = id,
    criteriaList = criteriaList
)
