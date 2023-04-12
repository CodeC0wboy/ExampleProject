package com.dnc.buendeal.core.core.data.response.product

import com.dnc.buendeal.core.core.data.response.MediaPreviews
import com.google.gson.annotations.SerializedName

data class SellerProductResponse(
    @SerializedName("media.domain") val mediaDomain: String?,
    @SerializedName("media.id") val mediaId: Long?,
    @SerializedName("media.label") val mediaLabel: String?,
    @SerializedName("media.languageid") val mediaLanguageId: String?,
    @SerializedName("media.mimetype") val mediaMimetype: String?,
    @SerializedName("media.preview") val mediaPreview: String?,
    @SerializedName("media.previews") val mediaPreviews: MediaPreviews?,
    @SerializedName("media.status") val mediaStatus: Int?,
    @SerializedName("media.type") val mediaType: String?,
    @SerializedName("media.url") val mediaUrl: String?,

    @SerializedName("price.costs") val priceCosts: Double?,
    @SerializedName("price.currencyid") val priceCurrencyId: String?,
    @SerializedName("price.domain") val priceDomain: String?,
    @SerializedName("price.id") val priceID: Long?,
    @SerializedName("price.label") val priceLabel: String?,
    @SerializedName("price.quantity") val priceQuantity: Long?,
    @SerializedName("price.rebate") val priceRebate: Double?,
    @SerializedName("price.status") val priceStatus: Int?,
    @SerializedName("price.taxflag") val priceTaxFlag: Boolean?,
    @SerializedName("price.taxrate") val priceTaxRate: Double?,
    @SerializedName("price.taxvalue") val priceTaxValue: Double?,
    @SerializedName("price.type") val priceType: String?,
    @SerializedName("price.value") val priceValue: Float?,

    @SerializedName("product.code") val productCode: String?,
    @SerializedName("product.config") val productConfig: List<Any?>,
    @SerializedName("product.ctime") val productCtime: String?,
    @SerializedName("product.dataset") val productDataset: String?,
    @SerializedName("product.dateend") val productDateEnd: String?,
    @SerializedName("product.datestart") val productDatestart: String?,
    @SerializedName("product.id") val productID: Long?,
    @SerializedName("product.label") val productLabel: String?,
    @SerializedName("product.rating") val productRating: Float?,
    @SerializedName("product.ratings") val productRatings: Float?,
    @SerializedName("product.scale") val productScale: Int?,
    @SerializedName("product.status") val productStatus: Long?,
    @SerializedName("product.target") val productTarget: String?,
    @SerializedName("product.type") val productType: String?,
    @SerializedName("product.url") val productURL: String?
)
