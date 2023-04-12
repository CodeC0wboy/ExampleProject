package com.dnc.buendeal.features.search.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.SortCriteria

data class SearchResultsSortFilterItemModel(
    val filterCriteriaList: List<CategoryFilterItemModel>,
    val sortCriteriaList: List<SortCriteria>
) : ItemModel
