package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.Description
import com.dnc.buendeal.core.core.domain.model.singleProduct.OptionColors
import com.google.gson.annotations.SerializedName

data class ProductMockResponse(
    @SerializedName("id") val id: String,
    @SerializedName("imageResource") val imageResource: Image,
    @SerializedName("title") val title: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("votes") val votes: Int,
    @SerializedName("price") val price: String,
    @SerializedName("newPrice") val newPrice: String,
    @SerializedName("currency") val currency: String?,
    @SerializedName("productMainAttribute") val productMainAttribute: List<ProductMainAttributeDto>? = null,
    @SerializedName("seller") val seller: SellerInfoMockResponse? = null,
    @SerializedName("description") val description: Description?,
    @SerializedName("specifications") val specifications: ProductMainAttributeDto?,
    @SerializedName("optionColors") val optionColors: OptionColors?,
    @SerializedName("favorite") val favorite: Boolean? = false,

)
