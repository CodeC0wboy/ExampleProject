package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.singleProduct.Color
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ColorItemModel(
    val id: Long,
    val name: String,
    val color: String,
    val idProduct: String,
    val selected: Boolean
) : ItemModel, Parcelable

fun Color.itemModel(selected: Boolean): ColorItemModel = ColorItemModel(
    id = id,
    name = name,
    color = color,
    idProduct = idProduct,
    selected = selected
)
