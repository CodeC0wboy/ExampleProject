package com.dnc.buendeal.core.core.data.response.comparison

import com.google.gson.annotations.SerializedName

data class ProductComparisonDataResponse(
    @SerializedName("product.id")
    val productID: String?,

    @SerializedName("product.url")
    val productURL: String?,

    @SerializedName("product.type")
    val productType: String?,

    @SerializedName("product.code")
    val productCode: String?,

    @SerializedName("product.label")
    val productLabel: String?,

    @SerializedName("product.status")
    val productStatus: Long?,

    @SerializedName("product.dataset")
    val productDataset: String,

    @SerializedName("product.datestart")
    val productDatestart: Any? = null,

    @SerializedName("product.dateend")
    val productDateend: Any? = null,

    @SerializedName("product.config")
    val productConfig: List<Any?>,

    @SerializedName("product.scale")
    val productScale: Long?,

    @SerializedName("product.target")
    val productTarget: String?,

    @SerializedName("product.ctime")
    val productCtime: String?,

    @SerializedName("product.rating")
    val productRating: String?,

    @SerializedName("product.ratings")
    val productRatings: Int?,

    @SerializedName("product.featured_at")
    val productFeaturedAt: String?,

    @SerializedName("product.siteid")
    val productSiteid: String?
)
