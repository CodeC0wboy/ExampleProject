package com.dnc.buendeal.features.products.domain

import com.dnc.buendeal.BuildConfig
import com.dnc.buendeal.core.core.data.datasource.ProductsDataSource
import com.dnc.buendeal.core.core.domain.model.FilterSort
import com.dnc.buendeal.core.core.domain.model.Product
import com.dnc.buendeal.core.extentions.mutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

interface ProductsListInteractor {
    suspend fun getProductsByCategory(categoryId: String?): List<Product>
    fun getProductsFlow(): StateFlow<List<Product>?>
    suspend fun updateStateProduct(id: String): StateFlow<List<Product>?>
    suspend fun getSortBy(): FilterSort
}

class ProductsListInteractorImpl(
    private val productsListLiveDataSource: ProductsDataSource,
    private val productsListMockDataSource: ProductsDataSource
) : ProductsListInteractor {
    private val productsByCategory: StateFlow<List<Product>?> = MutableStateFlow(null)

    override suspend fun getProductsByCategory(categoryId: String?): List<Product> =
        currentDataSource().getProductsByCategory(categoryId)
            .also { productsByCategory.mutable().value = it }

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

    override suspend fun getSortBy(): FilterSort = currentDataSource().getSortBy()

    private fun currentDataSource(): ProductsDataSource =
        if (BuildConfig.LIVE_PRODUCTS_AVAILABLE) {
            productsListLiveDataSource
        } else {
            productsListMockDataSource
        }
}
