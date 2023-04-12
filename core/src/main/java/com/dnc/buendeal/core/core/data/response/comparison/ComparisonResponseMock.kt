package com.dnc.buendeal.core.core.data.response.comparison

data class ComparisonResponseMock(
    val comparisonId: Int,
    val categoryLabel: String,
    val comparisonPairList: List<ComparisonPairDto>
)
