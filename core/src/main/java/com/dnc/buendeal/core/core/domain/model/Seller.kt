package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.SellerInfoMockResponse
import com.dnc.buendeal.core.core.data.response.SellerInfoResponse
import com.dnc.buendeal.core.core.domain.data.Image

data class Seller(
    val address: String,
    val description: String,
    val email: String,
    val image: Image,
    val name: String,
    val phone: String,
    val positiveReviews: Int,
    val productsCount: Int,
    val stars: Int,
    val reviews: Int,
    val reviewsCount: Int,
    val siteId: String,
    val url: String
)

fun SellerInfoMockResponse.domain(): Seller = Seller(
    address = address ?: "",
    description = about ?: "",
    email = email ?: "",
    image = imgRes,
    name = name,
    phone = phone ?: "",
    positiveReviews = votes ?: 0,
    productsCount = votes ?: 0,
    stars = stars,
    reviews = votes ?: 0,
    reviewsCount = votes ?: 0,
    siteId = siteId,
    url = url
)

fun SellerInfoResponse.domain(): Seller = Seller(
    address = address ?: "",
    description = description ?: "",
    email = email ?: "",
    image = Image.ImgUrl(image ?: ""),
    name = name ?: "",
    phone = phone ?: "",
    positiveReviews = positiveReviews ?: 0,
    productsCount = productsCount ?: 0,
    stars = rating?.toIntOrNull() ?: 0,
    reviews = reviews ?: 0,
    reviewsCount = reviewsCount ?: 0,
    siteId = siteid ?: "",
    url = url ?: ""
)
