package com.dnc.buendeal.core.core.ui.model.filter

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.filter.FilterInnerOptions
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterInnerOptionsItemModel(
    val id: Long,
    val label: String,
    var isChecked: Boolean? = false,

    var isPicked: Boolean? = null,
    var optionProductStock: String? = null,
    var optionProductSKU: String? = null,
    var optionProductPrice: String? = null,
    var optionProductDiscount: String? = null,

) : ItemModel, Parcelable {
    override fun toString(): String {
        return label
    }
}

@Parcelize
data class PickedFilters(
    val id: Long,
    val filterList: List<FilterInnerOptionsItemModel>
) : Parcelable

fun FilterInnerOptions.itemModel() = FilterInnerOptionsItemModel(
    id = id,
    label = label
)
