package com.dnc.buendeal.core.core.data.datasource

import com.dnc.buendeal.core.core.data.datasource.database.ProductDao
import com.dnc.buendeal.core.core.data.response.category.TypeResponse
import com.dnc.buendeal.core.core.data.response.domain
import com.dnc.buendeal.core.core.data.response.filter.FilterSortResponse
import com.dnc.buendeal.core.core.data.response.product.*
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.*
import com.dnc.buendeal.core.core.domain.model.singleProduct.Color
import com.dnc.buendeal.core.core.domain.model.singleProduct.OptionColors
import com.dnc.buendeal.core.core.domain.model.singleProduct.SingleProduct
import com.dnc.buendeal.core.extentions.concatImgUrl
import com.dnc.buendeal.core.extentions.mutable
import com.dnc.buendeal.core.network.ApiService
import com.dnc.buendeal.core.network.ApiWishlist
import com.dnc.buendeal.core.utils.Colors
import com.dnc.buendeal.core.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductsDataSource {
    suspend fun getProductsByCategory(categoryId: String? = null): List<Product>

    suspend fun refreshCategoryProducts()

    suspend fun getCategoryProductsFlow(): StateFlow<List<Product>>

    suspend fun getFeaturedProducts(): List<Product>

    suspend fun getProductsByPrice(
        categoryId: String? = null,
        upperValue: String?,
        lowerValue: String?
    ): List<Product>

    suspend fun getProductsByAppliedFilters(
        categoryId: String? = null,
        attributesId: List<String>?
    ): List<Product>

    suspend fun addProductsToRecentlyViewed(product: Product)

    suspend fun getViewedProducts(): List<Product>

    suspend fun getViewedProductsFlow(): StateFlow<List<Product>>

    suspend fun getSortBy(): FilterSort

    suspend fun refreshAssignedProduct()

    suspend fun getAssignedProductFlow(): StateFlow<List<AssignedProduct>>

    suspend fun getRecentlyViewedAssignedProducts(): List<AssignedProduct>

    suspend fun getProduct(productId: String?): SingleProduct

    suspend fun getProductsBySeller(
        sellerId: String,
        sortCriteria: SortCriteria = SortCriteria.LOWEST_PRICE
    ): List<Product>
}

class ProductsLiveDataSourceImpl(
    private val apiService: ApiService,
    private val api: ApiWishlist
) : ProductsDataSource {

    var productsList = arrayListOf<Product>()
    var catalogProductsFlow: StateFlow<List<Product>> = MutableStateFlow(arrayListOf())

    var catId: String? = null

    private val assignedProductsFlow: StateFlow<List<AssignedProduct>> =
        MutableStateFlow(arrayListOf())

    private val viewedProductsFlow: StateFlow<List<Product>> = MutableStateFlow(arrayListOf())

    override suspend fun getProductsByCategory(categoryId: String?): List<Product> {
        //apiRequest
        return arrayListOf()
    }

    override suspend fun refreshCategoryProducts() {
        //apiRequest
    }

    override suspend fun getCategoryProductsFlow() = catalogProductsFlow

    override suspend fun addProductsToRecentlyViewed(product: Product) {

    }

    override suspend fun getViewedProducts(): List<Product> {
        return arrayListOf()
    }

    override suspend fun getViewedProductsFlow() = viewedProductsFlow

    override suspend fun getFeaturedProducts(): List<Product> {
        //apiRequest
        return arrayListOf()
    }

    override suspend fun getProductsByPrice(
        categoryId: String?,
        upperValue: String?,
        lowerValue: String?
    ): List<Product> {
        //apiRequest
        return arrayListOf()
    }

    override suspend fun getProductsByAppliedFilters(
        categoryId: String?,
        attributesId: List<String>?
    ): List<Product> {
        //apiRequest
        return arrayListOf()
    }

    override suspend fun getProduct(productId: String?): SingleProduct {
        TODO()
    }

    override suspend fun getSortBy(): FilterSort = FilterSortResponse(
        0,
        listOf(
            SortCriteria.REVELANCE,
            SortCriteria.LOWEST_PRICE,
            SortCriteria.HIGHEST_PRICE,
            SortCriteria.BEST_RATED,
            SortCriteria.MOST_POPULAR,
            SortCriteria.DISCOUNTS,
            SortCriteria.NEWEST,
        )
    ).domain()

    override suspend fun refreshAssignedProduct() {
        assignedProductsFlow.mutable().value = listOf(
            AssignedProduct(id = SectionId.RECENTLY, products = getViewedProducts()),
            AssignedProduct(id = SectionId.FEATURED, products = getFeaturedProducts())
        )
    }

    override suspend fun getAssignedProductFlow() = assignedProductsFlow

    override suspend fun getRecentlyViewedAssignedProducts() = listOf(
        AssignedProduct(id = SectionId.RECENTLY, products = getViewedProducts())
    )

    override suspend fun getProductsBySeller(
        sellerId: String,
        sortCriteria: SortCriteria
    ): List<Product> {
// api request
        return arrayListOf()
    }


      enum class Currency(var Symbol: String) {
        USD("$")
    }

    enum class TextType(var textType: String) {
        LONG("long"),
        META_KEYWORDS("meta-keyword"),
        NAME("name")
    }
}
