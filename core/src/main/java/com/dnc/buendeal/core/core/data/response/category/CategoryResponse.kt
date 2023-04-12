package com.dnc.buendeal.core.core.data.response.category

import com.dnc.buendeal.core.core.data.response.LinksResponse
import com.dnc.buendeal.core.core.data.response.MetaResponse
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("meta")
    val meta: MetaResponse?,
    @SerializedName("links")
    val links: LinksResponse?,
    @SerializedName("data")
    val data: CategoryDataResponse?,
    @SerializedName("included")
    val included: List<IncludedResponse>?
)
