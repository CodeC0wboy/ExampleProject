package com.dnc.buendeal.features.search.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.features.search.domain.model.CategoryFilter

data class CategoryFilterItemModel(
    val id: String,
    val title: String
) : ItemModel

fun CategoryFilter.itemModel() = CategoryFilterItemModel(id = id, title = title)
