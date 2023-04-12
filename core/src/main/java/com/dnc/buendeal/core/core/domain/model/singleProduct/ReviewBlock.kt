package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.ReviewBlockResponse
import com.dnc.buendeal.core.core.domain.model.Review
import com.dnc.buendeal.core.core.domain.model.domain

data class ReviewBlock(
    val id: Long,
    val reviews: List<Review>,
    val quantityReview: Int
)

fun ReviewBlockResponse.domain(): ReviewBlock = ReviewBlock(
    id = id,
    reviews = reviews.map { it.domain() },
    quantityReview = quantityReview
)
