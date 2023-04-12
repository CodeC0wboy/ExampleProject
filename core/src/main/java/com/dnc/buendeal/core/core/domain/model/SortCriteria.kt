package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.R

enum class SortCriteria(val value: Int) {
    REVELANCE(R.string.relevance),
    LOWEST_PRICE(R.string.lowest_price),
    HIGHEST_PRICE(R.string.highest_price),
    BEST_RATED(R.string.best_rated),
    MOST_POPULAR(R.string.most_popular),
    DISCOUNTS(R.string.discounts),
    NEWEST(R.string.newest),
    NEW(R.string.new_),
    POPULAR(R.string.popular),
    ADD_DATE(R.string.add_date),
    RATING(R.string.rating),
    ANSWER(R.string.answer)
}
