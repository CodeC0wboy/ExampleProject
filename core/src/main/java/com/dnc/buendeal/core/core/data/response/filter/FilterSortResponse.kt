package com.dnc.buendeal.core.core.data.response.filter

import com.dnc.buendeal.core.core.domain.model.SortCriteria
import com.google.gson.annotations.SerializedName

data class FilterSortResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("criteriaList") val criteriaList: List<SortCriteria>
)
