package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Review
import java.util.*

data class ReviewItemModel(
    val id: Long,
    val username: String,
    val stars: Int,
    val date: Date?,
    val comment: String,
    val photos: List<Image>? = null,
    val productItemModel: ProductItemModel? = null
) : ItemModel

fun Review.itemModel(): ReviewItemModel = ReviewItemModel(
    id = id,
    username = username,
    stars = stars,
    date = date,
    comment = comment,
    photos = photos,
    productItemModel = product?.itemModel()
)
