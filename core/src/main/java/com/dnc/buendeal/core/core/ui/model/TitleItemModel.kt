package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel

data class TitleItemModel(
    val title: String,
    val isPlusButtonVisible: Boolean = false,
    val isBackButtonVisible: Boolean = true,
    val isControlsButtonVisible: Boolean = false,
) : ItemModel
