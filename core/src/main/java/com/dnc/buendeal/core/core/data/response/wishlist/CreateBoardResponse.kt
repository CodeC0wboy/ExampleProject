package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class CreateBoardResponse(
    @SerializedName("success")
    val success: Success
)
