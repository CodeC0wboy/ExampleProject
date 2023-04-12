package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.core.data.response.category.CSRFResponse
import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("total") val total: Int?,
    @SerializedName("prefix") val prefix: Any? = null,
    @SerializedName("contentBaseurl") val contentBaseurl: String?,
    @SerializedName("csrf") val csrf: CSRFResponse?
)
