package com.dnc.buendeal.core.core.data.response.comparison

import com.dnc.buendeal.core.core.domain.model.Product

data class ComparisonPairDto(
    val first: Product,
    val second: Product
)
