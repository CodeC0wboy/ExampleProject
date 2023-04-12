package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.CustomerQABlockResponse

data class CustomerQABlock(
    val id: Long,
    val quantityAnswers: Int?
)

fun CustomerQABlockResponse.domain() = CustomerQABlock(
    id = id,
    quantityAnswers = quantityAnswers
)
