package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.data.response.wishlist.BoardResponse
import com.dnc.buendeal.core.core.domain.model.Board
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BoardItemModel(
    val id: Int,
    val likesCount: Int,
    val name: String,
    var previewItems: @RawValue List<PhotoItemModel>,
    val totalProducts: Int
) : ItemModel, Parcelable

fun Board.itemModel(): BoardItemModel = BoardItemModel(
    id = id,
    likesCount = likesCount,
    name = name,
    previewItems = previewItems.map { PhotoItemModel(it) },
    totalProducts = totalProducts
)

fun BoardResponse.domain() = Board(
    id = id,
    likesCount = likesCount,
    name = name,
    previewItems = previewItems,
    totalProducts = totalProducts
)
