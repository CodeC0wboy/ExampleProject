package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.wishlist.BoardResponse

data class Board(
    val id: Int,
    val likesCount: Int,
    val name: String,
    val previewItems: List<String>,
    val totalProducts: Int
)

fun BoardResponse.domain() = Board(
    id = id,
    likesCount = likesCount,
    name = name,
    previewItems = previewItems,
    totalProducts = totalProducts
)
