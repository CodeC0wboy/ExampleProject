package com.dnc.buendeal.core.core.data.response.category

import com.dnc.buendeal.core.core.data.response.SelfResponse
import com.google.gson.annotations.SerializedName

data class DataLinksResponse(
    @SerializedName("self")
    val self: SelfResponse?
)
