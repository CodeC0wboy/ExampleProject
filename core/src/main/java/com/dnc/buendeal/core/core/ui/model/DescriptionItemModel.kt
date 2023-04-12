package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.Description
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DescriptionItemModel(
    val id: Long,
    val description: String
) : ItemModel, Parcelable

fun Description.itemModel() = DescriptionItemModel(
    id = id,
    description = description
)
