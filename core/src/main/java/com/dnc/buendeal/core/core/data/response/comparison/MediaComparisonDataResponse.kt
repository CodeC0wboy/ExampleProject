package com.dnc.buendeal.core.core.data.response.comparison

import com.google.gson.annotations.SerializedName

data class MediaComparisonDataResponse(
    @SerializedName("text.id")
    val textID: String?,

    @SerializedName("text.languageid")
    val textLanguageid: Any? = null,

    @SerializedName("text.type")
    val textType: String?,

    @SerializedName("text.label")
    val textLabel: String?,

    @SerializedName("text.domain")
    val textDomain: String?,

    @SerializedName("text.content")
    val textContent: String?,

    @SerializedName("text.status")
    val textStatus: Long?
)
