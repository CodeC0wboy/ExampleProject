package com.dnc.buendeal.core.core.domain.model

data class TitleSection(
    val title: String,
    val visibility: Boolean,
    val id: SectionId
)

enum class SectionId {
    RECENTLY,
    FEATURED,
    ONSALE,
    TOPSELLERS,
    TOP_DEALS,
    BUENDAL_CHOICE,
    PRODUCTS_GAINING_IN_POPULARITY,
    PEOPLE_CONSIDERED,
}
