package com.dnc.buendeal.core.core.domain.model.singleProduct

import com.dnc.buendeal.core.core.data.response.singleProduct.SingleProductResponse
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product

data class SingleProduct(
    val id: Long,
    val productImagesResource: List<Image>,
    val product: Product
)

fun SingleProductResponse.domain(): SingleProduct = SingleProduct(
    id = id,
    productImagesResource = productImagesResource,
    product = product
)
