package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.filter.FilterSortResponse

data class FilterSort(
    val id: Long,
    val criteriaList: List<SortCriteria>
)

fun FilterSortResponse.domain(): FilterSort = FilterSort(
    id = id,
    criteriaList = criteriaList
)
