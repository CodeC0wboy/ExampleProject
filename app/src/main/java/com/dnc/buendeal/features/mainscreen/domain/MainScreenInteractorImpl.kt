package com.dnc.buendeal.features.mainscreen.domain

import com.dnc.buendeal.BuildConfig
import com.dnc.buendeal.R
import com.dnc.buendeal.core.core.data.datasource.ProductsDataSource
import com.dnc.buendeal.core.core.data.datasource.PromotionsBannerDataSource
import com.dnc.buendeal.core.core.domain.model.*
import com.dnc.buendeal.core.extentions.mutable
import com.dnc.buendeal.core.providers.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

interface MainScreenInteractor {
    suspend fun getProducts(): List<Product>
    suspend fun getBannersByCategory(catalogId: String): List<Banner>
    suspend fun refreshAssignedProduct()
    suspend fun getAssignedProductFlow(): StateFlow<List<AssignedProduct>>
    suspend fun getTitleSection(): List<TitleSection>
    fun getProductsFlow(): StateFlow<List<Product>?>
    suspend fun updateStateProduct(id: String): StateFlow<List<Product>?>
}

class MainScreenInteractorImpl(
    private val promotionsLiveDataSource: PromotionsBannerDataSource,
    private val promotionsMockDataSource: PromotionsBannerDataSource,
    private val productsListLiveDataSource: ProductsDataSource,
    private val productsListMockDataSource: ProductsDataSource,
    private val resourceProvider: ResourceProvider
) : MainScreenInteractor {

    private val productsByCategory: StateFlow<List<Product>?> = MutableStateFlow(null)

    override suspend fun getProducts(): List<Product> =
        currentProductsDataSource().getProductsByCategory()
            .also { productsByCategory.mutable().value = it }

    override suspend fun getTitleSection(): List<TitleSection> {
        return listOf(
            TitleSection(resourceProvider.getString(R.string.recently_viewed), true, SectionId.RECENTLY),
            TitleSection(resourceProvider.getString(R.string.featured_products), true, SectionId.FEATURED)
        )
    }


    override suspend fun getBannersByCategory(catalogId: String): List<Banner> =
        currentBannerDataSource().getBannersByCategory(catalogId)

    override suspend fun refreshAssignedProduct() = currentProductsDataSource().refreshAssignedProduct()

    override suspend fun getAssignedProductFlow(): StateFlow<List<AssignedProduct>> =
        currentProductsDataSource().getAssignedProductFlow()

    override fun getProductsFlow(): StateFlow<List<Product>?> =
        productsByCategory



    override suspend fun updateStateProduct(id: String): StateFlow<List<Product>?> {
        productsByCategory.mutable().value = withContext(Dispatchers.IO) {
            productsByCategory.value?.map {
                if (it.id == id) it.copy(favorite = true)
                else it
            }
        }
        return productsByCategory
    }

    private fun currentBannerDataSource(): PromotionsBannerDataSource =
        if (BuildConfig.LIVE_PROMOTIONS_AVAILABLE) {
            promotionsLiveDataSource
        } else {
            promotionsMockDataSource
        }

    private fun currentProductsDataSource(): ProductsDataSource =
        if (BuildConfig.LIVE_PRODUCTS_AVAILABLE) {
            productsListLiveDataSource
        } else {
            productsListMockDataSource
        }

}
