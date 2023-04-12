package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class BoardResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("products") val products: List<ProductMockResponse>,
    @SerializedName("totalPrice") val totalPrice: String,
)
