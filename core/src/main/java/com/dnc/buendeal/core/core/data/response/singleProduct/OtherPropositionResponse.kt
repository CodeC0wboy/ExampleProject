package com.dnc.buendeal.core.core.data.response.singleProduct

import com.dnc.buendeal.core.core.data.response.SellerInfoResponse

data class OtherPropositionResponse(
    val id: Long,
    val price: String,
    val sellerList: List<SellerInfoResponse>
)
