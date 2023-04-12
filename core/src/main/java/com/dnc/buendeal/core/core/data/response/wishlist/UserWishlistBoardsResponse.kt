package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class UserWishlistBoardsResponse(
    @SerializedName("boards")
    val boards: List<BoardResponse>,
    @SerializedName("pagination")
    val pagination: PaginationResponse
)
