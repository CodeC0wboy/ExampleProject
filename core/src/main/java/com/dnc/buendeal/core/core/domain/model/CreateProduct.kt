package com.dnc.buendeal.core.core.domain.model

import java.io.File

data class CreateProduct(
    val sku: String,
    val name: Map<String, String>,
    val description: Map<String, String>,
    val category: String,
    val subCategory: String,
    val childSubCategory: String,
    val sellerUrl: String,
    val price: String,
    val rebate: String,
    val quantity: String,
    val image: List<File>,
    val seo: List<String>,
    val options: Map<String, String>?, // TODO need to change type
    val stock: Int?,
    val specs: List<String>?,
    val featuredImageName: String?,
    val autoTranslate: Boolean?
)
