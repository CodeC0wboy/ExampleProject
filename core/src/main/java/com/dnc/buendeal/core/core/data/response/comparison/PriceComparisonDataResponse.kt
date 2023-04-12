package com.dnc.buendeal.core.core.data.response.comparison

import com.dnc.buendeal.core.core.data.response.product.ProductPriceTaxRatesResponse
import com.google.gson.annotations.SerializedName

data class PriceComparisonDataResponse(
    @SerializedName("price.id")
    val priceID: String?,

    @SerializedName("price.type")
    val priceType: String?,

    @SerializedName("price.currencyid")
    val priceCurrencyid: String?,

    @SerializedName("price.domain")
    val priceDomain: String?,

    @SerializedName("price.quantity")
    val priceQuantity: Long?,

    @SerializedName("price.value")
    val priceValue: String?,

    @SerializedName("price.costs")
    val priceCosts: String?,

    @SerializedName("price.rebate")
    val priceRebate: String?,

    @SerializedName("price.taxvalue")
    val priceTaxvalue: String?,

    @SerializedName("price.taxrates")
    val priceTaxrates: ProductPriceTaxRatesResponse,

    @SerializedName("price.taxrate")
    val priceTaxrate: String?,

    @SerializedName("price.taxflag")
    val priceTaxflag: Boolean?,

    @SerializedName("price.status")
    val priceStatus: Long?,

    @SerializedName("price.label")
    val priceLabel: String?
)
