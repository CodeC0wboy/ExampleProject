package com.dnc.buendeal.features.search.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.RecentSearch

data class RecentSearchItemModel(
    val title: String
) : ItemModel

fun RecentSearch.itemModel(): RecentSearchItemModel = RecentSearchItemModel(
    title = title
)
