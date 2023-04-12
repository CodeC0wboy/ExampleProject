package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.Banner

data class BannerItemModel(
    val id: Long?,
    val link: String?,
    val url: String?
) : ItemModel

fun Banner.itemModel() = BannerItemModel(
    id = id,
    link = link,
    url = url
)
