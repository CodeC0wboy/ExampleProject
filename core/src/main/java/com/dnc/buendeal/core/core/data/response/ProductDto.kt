package com.dnc.buendeal.core.core.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product
import java.util.*

@Entity
data class ProductDto(
    @PrimaryKey val id: Int,
    val imageResource: String,
    val isResId: Boolean,
    val title: String,
    val stars: Int,
    val votes: Int,
    val price: String,
    val newPrice: String,
    val currency: String,
    val favorite: Boolean = false,
    val sellerId: String = "",
    val dateAdded: Date
)

fun ProductDto.domain(): Product = Product(
    id = id.toString(),
    imageResource = if (isResId) Image.ImgRes(imageResource.toInt()) else Image.ImgUrl(imageResource),
    title = title,
    stars = stars,
    votes = votes,
    price = price,
    newPrice = newPrice,
    currency = currency ?: "",
    productMainAttribute = null,
    seller = null,
    description = null,
    specifications = null,
    optionColors = null,
    favorite = favorite ?: false,
    sellerId = "1.3.",
)
