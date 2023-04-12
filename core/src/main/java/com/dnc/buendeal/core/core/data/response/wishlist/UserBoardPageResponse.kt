package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class UserBoardPageResponse(
    @SerializedName("board")
    val board: AddItemInWishlistResponse,
    @SerializedName("items")
    val items: List<UserWishlistResponse>,
    @SerializedName("pagination")
    val pagination: PaginationResponse
)
