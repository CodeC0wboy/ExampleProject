package com.dnc.buendeal.features.search.domain.model

import com.dnc.buendeal.features.search.data.response.CategoryFilterResponse

data class CategoryFilter(
    val id: String,
    val title: String
)

fun CategoryFilterResponse.domain() = CategoryFilter(id = id, title = title)
