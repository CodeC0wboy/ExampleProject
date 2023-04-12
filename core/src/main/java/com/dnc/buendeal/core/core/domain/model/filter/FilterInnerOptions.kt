package com.dnc.buendeal.core.core.domain.model.filter

import com.dnc.buendeal.core.core.data.response.filter.FilterInnerOptionsResponse

data class FilterInnerOptions(
    val id: Long,
    val label: String
)

fun FilterInnerOptionsResponse.domain() = id?.let {
    FilterInnerOptions(
        id = it,
        label = label ?: " "
    )
}
