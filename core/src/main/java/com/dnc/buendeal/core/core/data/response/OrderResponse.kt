package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.core.domain.model.Product

data class OrderResponse(
    val id: String,
    val date: String,
    val status: OrderStatus,
    val amount: String,
    val products: List<Product>
)
