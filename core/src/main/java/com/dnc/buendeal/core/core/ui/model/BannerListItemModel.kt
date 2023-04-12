package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel

data class BannerListItemModel(
    val id: Long,
    val banners: List<BannerItemModel>
) : ItemModel
