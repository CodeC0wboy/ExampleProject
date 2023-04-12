package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.SectionId

data class ProductsListItemModel(
    val id: SectionId,
    val products: List<ProductItemModel>
) : ItemModel
