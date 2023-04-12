package com.dnc.buendeal.core.core.data.response.singleProduct

import com.dnc.buendeal.core.core.data.response.ReviewResponse

data class ReviewBlockResponse(
    val id: Long,
    val reviews: List<ReviewResponse>,
    val quantityReview: Int
)
