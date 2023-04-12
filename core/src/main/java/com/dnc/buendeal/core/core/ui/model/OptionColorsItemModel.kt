package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.singleProduct.OptionColors
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OptionColorsItemModel(
    val id: Long,
    val colors: List<ColorItemModel>
) : ItemModel, Parcelable

fun OptionColors.itemModel() = OptionColorsItemModel(
    id = id,
    colors = colors.map { it.itemModel(false) }
)
