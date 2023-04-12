package com.dnc.buendeal.core.core.data.response.comparison

import com.google.gson.annotations.SerializedName

data class ComparisonResponse(
    @SerializedName("category")
    val category: CategoryComparisonResponse?,
    @SerializedName("products")
    val products: ArrayList<ProductComparisonResponse>?,
    @SerializedName("count")
    val count: Long?
)
