package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.RecentSearchResponse

data class RecentSearch(
    val title: String
)

fun RecentSearchResponse.domain(): RecentSearch = RecentSearch(
    title = title
)
