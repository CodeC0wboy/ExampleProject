package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.SectionId
import com.dnc.buendeal.core.core.domain.model.TitleSection

data class TitleSectionItemModel(
    val title: String,
    val visibility: Boolean,
    val id: SectionId
) : ItemModel

fun TitleSection.itemModel(): TitleSectionItemModel = TitleSectionItemModel(
    title = title,
    visibility = visibility,
    id = id
)
