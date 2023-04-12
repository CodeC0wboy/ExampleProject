package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.BannerResponse

data class Banner(
    val id: Long?,
    val link: String?,
    val url: String?
)

fun BannerResponse.domain() = Banner(
    id = id,
    link = link,
    url = url
)
