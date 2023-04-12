package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.OtherPropositionResponse
import com.dnc.buendeal.core.core.domain.model.Seller
import com.dnc.buendeal.core.core.domain.model.domain

data class OtherProposition(
    val id: Long,
    val price: String,
    val sellerList: List<Seller>
)

fun OtherPropositionResponse.domain(): OtherProposition = OtherProposition(
    id = id,
    price = price,
    sellerList = sellerList.map { it.domain() }
)
