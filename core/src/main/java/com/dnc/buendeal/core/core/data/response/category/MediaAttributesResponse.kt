package com.dnc.buendeal.core.core.data.response.category

import com.google.gson.annotations.SerializedName

data class MediaAttributesResponse(
    @SerializedName("catalog.lists.id")
    val catalogListsID: String?,

    @SerializedName("catalog.lists.domain")
    val catalogListsDomain: TypeResponse?,

    @SerializedName("catalog.lists.refid")
    val catalogListsRefid: String?,

    @SerializedName("catalog.lists.datestart")
    val catalogListsDatestart: Any? = null,

    @SerializedName("catalog.lists.dateend")
    val catalogListsDateend: Any? = null,

    @SerializedName("catalog.lists.config")
    val catalogListsConfig: List<Any?>,

    @SerializedName("catalog.lists.position")
    val catalogListsPosition: Int?,

    @SerializedName("catalog.lists.status")
    val catalogListsStatus: Int?,

    @SerializedName("catalog.lists.type")
    val catalogListsType: CatalogListsTypeResponse?
)
