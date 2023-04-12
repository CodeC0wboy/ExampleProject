package com.dnc.buendeal.core.core.ui.model.filter

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.filter.Filter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterItemModel(
    val id: Long,
    val name: String,
    var items: List<FilterInnerOptionsItemModel>,
    var nothingSelected: Boolean? = true
) : ItemModel, Parcelable {
    override fun toString(): String {
        return name
    }
}

fun Filter.itemModel() = FilterItemModel(
    id = id,
    name = name,
    items = items.map { it.itemModel() }
)
