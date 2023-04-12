package com.dnc.buendeal.core.core.data.response.wishlist

import com.google.gson.annotations.SerializedName

data class Success(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
