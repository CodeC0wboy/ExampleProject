package com.dnc.buendeal.core.core.data.response.comparison

import com.google.gson.annotations.SerializedName

data class MediaComparisonResponse(
    @SerializedName("media.id")
    val mediaID: String?,

    @SerializedName("media.domain")
    val mediaDomain: String?,

    @SerializedName("media.label")
    val mediaLabel: String?,

    @SerializedName("media.languageid")
    val mediaLanguageid: Any? = null,

    @SerializedName("media.mimetype")
    val mediaMimetype: String?,

    @SerializedName("media.type")
    val mediaType: String?,

    @SerializedName("media.preview")
    val mediaPreview: String?,

    @SerializedName("media.previews")
    val mediaPreviews: List<String>?,

    @SerializedName("media.url")
    val mediaURL: String?,

    @SerializedName("media.status")
    val mediaStatus: Long?
)
