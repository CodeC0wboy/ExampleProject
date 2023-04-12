package com.dnc.buendeal.core.core.ui.model

import android.os.Parcelable
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductItemModel(
    val id: String,
    val imageResource: Image,
    val title: String,
    val stars: Int,
    val votes: Int,
    val price: String,
    val newPrice: String,
    val currency: String,
    val productMainAttributes: List<ProductMainAttributeItemModel>? = null,
    val seller: SellerItemModel? = null,
    val description: DescriptionItemModel?,
    val specifications: ProductMainAttributeItemModel?,
    val optionColors: OptionColorsItemModel?,
    var favorite: Boolean = false,
    val sellerId: String = ""
) : ItemModel, Parcelable

fun Product.itemModel(): ProductItemModel = ProductItemModel(
    id = id,
    imageResource = imageResource,
    title = title,
    stars = stars,
    votes = votes,
    price = price,
    newPrice = newPrice,
    currency = currency,
    productMainAttributes = productMainAttribute?.map { it.itemModel() },
    seller = seller?.itemModel(),
    description = description?.itemModel(),
    specifications = specifications?.itemModel(),
    optionColors = optionColors?.itemModel(),
    favorite = favorite,
    sellerId = sellerId
)
