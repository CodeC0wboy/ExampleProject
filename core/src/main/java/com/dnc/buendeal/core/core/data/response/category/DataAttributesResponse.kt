package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class DataAttributesResponse(
    @SerializedName("catalog.url")
    val catalogURL: String?,

    @SerializedName("catalog.code")
    val catalogCode: String?,

    @SerializedName("catalog.label")
    val catalogLabel: String?,

    @SerializedName("catalog.config")
    val catalogConfig: List<Any?>?,

    @SerializedName("catalog.status")
    val catalogStatus: Int?,

    @SerializedName("catalog.target")
    val catalogTarget: String?,

    @SerializedName("catalog.hasChildren")
    val catalogHasChildren: Boolean?
)
