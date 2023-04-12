package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

enum class MediaMimetypeResponse(val value: String) {
    @SerializedName("image/png")
    ImagePNG("image/png"),

    @SerializedName("image/svg+xml")
    ImageSVGXML("image/svg+xml");
}
