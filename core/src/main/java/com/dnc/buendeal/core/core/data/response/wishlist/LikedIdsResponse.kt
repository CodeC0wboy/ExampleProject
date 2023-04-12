package com.dnc.buendeal.core.core.data.response.wishlist

import com.dnc.buendeal.core.core.domain.model.LikedIdsDto
import com.google.gson.annotations.SerializedName

data class LikedIdsResponse(
    @SerializedName("data")
    val ids: List<Int>
)

fun LikedIdsResponse.domain(): LikedIdsDto = LikedIdsDto(ids = ids)
