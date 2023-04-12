package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoItemModel(val url: String) : ItemModel, Parcelable
