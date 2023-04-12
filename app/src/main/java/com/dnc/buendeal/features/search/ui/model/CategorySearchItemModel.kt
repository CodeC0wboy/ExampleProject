package com.dnc.buendeal.features.search.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.features.search.domain.model.CategorySearch

data class CategorySearchItemModel(
    val title: String
) : ItemModel

fun CategorySearch.itemModel(): CategorySearchItemModel = CategorySearchItemModel(
    title = title
)
