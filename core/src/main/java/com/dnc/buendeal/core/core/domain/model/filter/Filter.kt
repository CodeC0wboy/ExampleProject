package com.dnc.buendeal.core.core.domain.model.filter

import com.dnc.buendeal.core.core.data.response.filter.FilterResponse

data class Filter(
    val id: Long,
    val name: String,
    val items: List<FilterInnerOptions>,
)

fun FilterResponse.domain() = id?.let {
    Filter(
        id = it,
        name = name ?: " ",
        items = items?.map { item -> item.domain() } as List<FilterInnerOptions>
    )
}
