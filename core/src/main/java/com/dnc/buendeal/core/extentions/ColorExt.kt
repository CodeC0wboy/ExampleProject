package com.dnc.buendeal.core.extentions

import android.content.Context
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.core.data.response.OrderStatus

fun OrderStatus.colorRes(context: Context) = when (this) {
    OrderStatus.UNPAID -> {
        context.getColor(R.color.red)
    }
    OrderStatus.SHIPPING -> {
        context.getColor(R.color.orange)
    }
    OrderStatus.SHIPPED -> {
        context.getColor(R.color.orange)
    }
    OrderStatus.PAID -> {
        context.getColor(R.color.green)
    }
    OrderStatus.COMPLETED -> {
        context.getColor(R.color.green)
    }
    else -> context.getColor(R.color.green)
}
