package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.ProductMainAttribute
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductMainAttributeItemModel(
    val label: String,
    val innerAttributes: List<ProductInnerAttributeItemModel>
) : ItemModel, Parcelable

fun ProductMainAttribute.itemModel(): ProductMainAttributeItemModel = ProductMainAttributeItemModel(
    label = label,
    innerAttributes = innerAttributes.map { it.itemModel() }
)
