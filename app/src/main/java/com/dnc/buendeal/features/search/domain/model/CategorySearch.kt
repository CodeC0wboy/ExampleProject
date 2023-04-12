package com.dnc.buendeal.features.search.domain.model

import com.dnc.buendeal.features.search.data.response.CategorySearchResponse

data class CategorySearch(
    val title: String
)

fun CategorySearchResponse.domain(): CategorySearch = CategorySearch(
    title = title
)
