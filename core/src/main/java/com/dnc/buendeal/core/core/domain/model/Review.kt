package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.ReviewResponse
import com.dnc.buendeal.core.core.data.response.SellerReviewResponse
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.extentions.toDate
import java.util.*

data class Review(
    val id: Long,
    val username: String,
    val stars: Int,
    val date: Date?,
    val comment: String,
    val photos: List<Image>? = null,
    val product: Product? = null
)

fun ReviewResponse.domain(): Review = Review(
    id = id,
    username = username,
    stars = stars,
    date = date.toDate(),
    comment = comment,
    photos = photos,
    product = product
)

fun SellerReviewResponse.domain(): Review {
    return Review(
        id = id ?: 0L,
        username = author ?: "",
        stars = rating ?: 0,
        date = ctime?.toDate(),
        comment = text ?: "",
        photos = media?.map { Image.ImgUrl(it) }
    )
}
