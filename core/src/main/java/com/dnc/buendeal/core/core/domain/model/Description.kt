package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.DescriptionResponse

data class Description(
    val id: Long,
    val description: String
)

fun DescriptionResponse.domain() = Description(
    id = id,
    description = description
)
