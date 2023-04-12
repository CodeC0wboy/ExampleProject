package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.category.CategoryMockResponse
import com.dnc.buendeal.core.core.domain.data.Image

data class Category(
    val id: String,
    val imageResource: Image,
    val title: String,
)

fun CategoryMockResponse.domain() = Category(
    id = id,
    imageResource = imageResource,
    title = title
)

data class SecondLevelCategories(
    val id: Long,
    val categories: List<Category>
)
