package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.ProductDto
import com.dnc.buendeal.core.core.data.response.ProductMockResponse
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.data.entity
import com.dnc.buendeal.core.core.domain.model.singleProduct.OptionColors
import java.util.*

data class Product(
    val id: String,
    val imageResource: Image,
    val title: String,
    val stars: Int,
    val votes: Int,
    val price: String,
    val newPrice: String,
    val currency: String,
    val productMainAttribute: List<ProductMainAttribute>? = null,
    val seller: Seller? = null,
    val description: Description? = null,
    val specifications: ProductMainAttribute? = null,
    val optionColors: OptionColors? = null,
    var favorite: Boolean = false,
    val sellerId: String = ""
)

fun ProductMockResponse.domain(): Product = Product(
    id = id,
    imageResource = imageResource,
    title = title,
    stars = stars,
    votes = votes,
    price = price,
    newPrice = newPrice,
    currency = currency ?: "",
    productMainAttribute = productMainAttribute?.map { it.domain() },
    seller = seller?.domain(),
    description = description,
    specifications = specifications?.domain(),
    optionColors = optionColors,
    favorite = favorite ?: false,
    sellerId = "1.3."
)

fun Product.entity(): ProductDto = ProductDto(
    id = id.toInt(),
    imageResource = imageResource.entity(),
    isResId = imageResource is Image.ImgRes,
    title = title,
    stars = stars,
    votes = votes,
    price = price,
    newPrice = newPrice,
    currency = currency ?: "",
    favorite = favorite ?: false,
    sellerId = "1.3.",
    dateAdded = Date()
)
