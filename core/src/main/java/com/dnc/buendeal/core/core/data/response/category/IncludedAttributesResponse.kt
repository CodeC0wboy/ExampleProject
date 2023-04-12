package com.dnc.buendeal.core.core.data.response.category

import com.dnc.buendeal.core.core.data.response.MediaPreviews
import com.google.gson.annotations.SerializedName

data class IncludedAttributesResponse(
    @SerializedName("catalog.url")
    val catalogURL: String? = null,

    @SerializedName("catalog.code")
    val catalogCode: String? = null,

    @SerializedName("catalog.label")
    val catalogLabel: String? = null,

    @SerializedName("catalog.config")
    val catalogConfig: List<Any?>? = null,

    @SerializedName("catalog.status")
    val catalogStatus: Long? = null,

    @SerializedName("catalog.target")
    val catalogTarget: String? = null,

    @SerializedName("catalog.hasChildren")
    val catalogHasChildren: Boolean? = null,

    @SerializedName("media.id")
    val mediaID: String? = null,

    @SerializedName("media.domain")
    val mediaDomain: TypeResponse? = null,

    @SerializedName("media.label")
    val mediaLabel: String? = null,

    @SerializedName("media.languageid")
    val mediaLanguageid: String? = null,

    @SerializedName("media.mimetype")
    val mediaMimetype: MediaMimetypeResponse? = null,

    @SerializedName("media.type")
    val mediaType: String? = null,

    @SerializedName("media.preview")
    val mediaPreview: String? = null,

    @SerializedName("media.previews")
    val mediaPreviews: MediaPreviews? = null,

    @SerializedName("media.url")
    val mediaURL: String? = null,

    @SerializedName("media.status")
    val mediaStatus: Int? = null
)
