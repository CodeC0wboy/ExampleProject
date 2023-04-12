package com.dnc.buendeal.core.core.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductTextAttributesResponse(
    @SerializedName("product.lists.id")
    val productListsID: String?,

    @SerializedName("product.lists.domain")
    val productListsDomain: String?,

    @SerializedName("product.lists.refid")
    val productListsRefid: String?,

    @SerializedName("product.lists.datestart")
    val productListsDatestart: Any? = null,

    @SerializedName("product.lists.dateend")
    val productListsDateend: Any? = null,

    @SerializedName("product.lists.config")
    val productListsConfig: List<Any?>,

    @SerializedName("product.lists.position")
    val productListsPosition: Long?,

    @SerializedName("product.lists.status")
    val productListsStatus: Long?,

    @SerializedName("product.lists.type")
    val productListsType: String?
)
