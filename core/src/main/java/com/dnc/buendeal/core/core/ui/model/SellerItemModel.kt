package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Seller
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SellerItemModel(
    val profileImage: Image,
    val name: String,
    val stars: Int,
    val votes: Int,
    val positiveFeedback: Int,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val siteId: String
) : ItemModel, Parcelable

fun Seller.itemModel(): SellerItemModel = SellerItemModel(
    profileImage = image,
    name = name,
    stars = stars,
    votes = reviewsCount,
    positiveFeedback = positiveReviews,
    email = email,
    phone = phone,
    address = address,
    about = description,
    siteId = siteId
)
