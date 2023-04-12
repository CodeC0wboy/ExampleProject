package com.dnc.buendeal.core.core.data.response

enum class OrderStatus(var value: String) {
    ALL("All"),
    UNPAID("Unpaid"),
    PAID("Paid"),
    SHIPPING("Shipping"),
    SHIPPED("Shipped"),
    COMPLETED("Completed")
}
