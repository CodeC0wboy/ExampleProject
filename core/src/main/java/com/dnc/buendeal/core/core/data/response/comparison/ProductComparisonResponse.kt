package com.dnc.buendeal.core.core.data.response.comparison

import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Product
import com.dnc.buendeal.core.core.domain.model.ProductInnerAttribute
import com.dnc.buendeal.core.core.domain.model.ProductMainAttribute
import com.dnc.buendeal.core.extentions.concatImgUrl
import com.google.gson.annotations.SerializedName

data class ProductComparisonResponse(
    @SerializedName("product")
    val product: ProductComparisonDataResponse?,
    @SerializedName("media")
    val media: List<MediaComparisonResponse>?,
    @SerializedName("text")
    val text: List<MediaComparisonDataResponse>?,
    @SerializedName("price")
    val price: List<PriceComparisonDataResponse>?,
    @SerializedName("attribute")
    val attribute: List<AttributeComparisonResponse>?
)

fun ProductComparisonResponse.domain(): Product {
    this.apply {

        val id = product?.productID ?: ""
        val stars = product?.productRating?.toFloat() ?: 0
        val votes = product?.productRatings ?: 0
        val title = product?.productLabel ?: ""
        val imageResource: Image =
            Image.ImgUrl(media?.get(0)?.mediaPreview?.concatImgUrl() ?: "")
        var priceValue = ""
        var newPrice = ""
        var currency = ""

        price?.get(0)?.let {
            if (it.priceValue != null && it.priceRebate != null) {
                currency = it.priceCurrencyid ?: ""
                priceValue = it.priceValue
                newPrice = String.format(
                    "%.2f",
                    (it.priceValue.toFloat() - it.priceRebate.toFloat())
                )
            }
        }

        val specs = arrayListOf<ProductInnerAttribute>()

        attribute?.forEach {
            specs.add(it.domain())
        }

        return Product(
            id = id,
            imageResource = imageResource,
            title = title,
            stars = stars.toInt(),
            votes = votes,
            price = priceValue,
            newPrice = newPrice,
            currency = currency,
            description = null,
            specifications = ProductMainAttribute("", specs),
            optionColors = null,
        )
    }
}
