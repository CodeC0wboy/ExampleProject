package com.dnc.buendeal.core.core.data.response.comparison

import com.dnc.buendeal.core.core.domain.model.ProductInnerAttribute
import com.google.gson.annotations.SerializedName

data class AttributeComparisonResponse(
    @SerializedName("attribute.id")
    val attributeID: String?,

    @SerializedName("attribute.domain")
    val attributeDomain: String?,

    @SerializedName("attribute.type")
    val attributeType: String?,

    @SerializedName("attribute.code")
    val attributeCode: String?,

    @SerializedName("attribute.label")
    val attributeLabel: String?,

    @SerializedName("attribute.status")
    val attributeStatus: Long?,

    @SerializedName("attribute.position")
    val attributePosition: Long?
)

fun AttributeComparisonResponse.domain(): ProductInnerAttribute = ProductInnerAttribute(
    // this.attributeID ?: "",
    this.attributeType ?: "",
    this.attributeLabel ?: ""
)
