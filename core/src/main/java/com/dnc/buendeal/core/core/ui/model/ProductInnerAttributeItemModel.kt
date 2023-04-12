package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.ProductInnerAttribute
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductInnerAttributeItemModel(
    val attrName: String,
    val attrValue: String?
) : ItemModel, Parcelable

fun ProductInnerAttribute.itemModel(): ProductInnerAttributeItemModel =
    ProductInnerAttributeItemModel(
        attrName = attrName,
        attrValue = attrValue
    )

fun ProductInnerAttributeItemModel.domain(): ProductInnerAttribute =
    ProductInnerAttribute(
        attrName = attrName,
        attrValue = attrValue
    )
