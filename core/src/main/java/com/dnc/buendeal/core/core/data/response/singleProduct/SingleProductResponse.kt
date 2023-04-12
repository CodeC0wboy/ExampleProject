package com.dnc.buendeal.core.core.data.response.singleProduct

import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product

data class SingleProductResponse(
    val id: Long,
    val productImagesResource: List<Image>,
    val product: Product
)
