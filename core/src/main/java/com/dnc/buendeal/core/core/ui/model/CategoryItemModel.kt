package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItemModel(
    val id: String,
    val imageResource: Image,
    val title: String,
) : ItemModel, Parcelable {

    override fun toString(): String {
        return title
    }
}

fun Category.itemModel() = CategoryItemModel(
    id = id,
    imageResource = imageResource,
    title = title
)
