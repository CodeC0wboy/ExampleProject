package com.dnc.buendeal.features.search.domain

import com.dnc.buendeal.BuildConfig
import com.dnc.buendeal.core.core.data.datasource.ProductsDataSource
import com.dnc.buendeal.core.core.domain.model.FilterSort
import com.dnc.buendeal.core.core.domain.model.Product
import com.dnc.buendeal.core.core.domain.model.RecentSearch
import com.dnc.buendeal.features.search.data.datasource.SearchDataSource
import com.dnc.buendeal.features.search.domain.model.CategoryFilter
import com.dnc.buendeal.features.search.domain.model.CategorySearch

interface SearchInteractor {
    suspend fun getRecent(): List<RecentSearch>
    suspend fun saveSearch(query: RecentSearch)
    suspend fun getCategories(): List<CategorySearch>
    suspend fun getSortBy(): FilterSort
    suspend fun getCategoryFilters(): List<CategoryFilter>
    suspend fun getProducts(query: String, categoryId: String?): List<Product>
}

class SearchInteractorImpl(
    private val searchDataSourceMock: SearchDataSource,
    private val searchDataSourceLive: SearchDataSource,
    private val productsDataSourceMock: ProductsDataSource,
    private val productsDataSourceLive: ProductsDataSource
) : SearchInteractor {

    override suspend fun getRecent(): List<RecentSearch> = currentDataSource().getRecent()
    override suspend fun saveSearch(query: RecentSearch) = currentDataSource().saveSearch(query)

    override suspend fun getCategories(): List<CategorySearch> = currentDataSource().getCategories()
    override suspend fun getSortBy(): FilterSort = currentDataSource().getSortBy()
    override suspend fun getCategoryFilters(): List<CategoryFilter> =
        currentDataSource().getCategoryFilters()

    override suspend fun getProducts(query: String, categoryId: String?): List<Product> =
        when (categoryId) {
            null -> currentDataSource().getProducts(query)
            else -> currentDataSource().getProducts(query)
        }

    private fun currentDataSource(): SearchDataSource =
        if (BuildConfig.LIVE_SEARCH_AVAILABLE) {
            searchDataSourceLive
        } else {
            searchDataSourceMock
        }

    private fun currentProductDataSource(): ProductsDataSource =
        if (BuildConfig.LIVE_SEARCH_AVAILABLE) {
            productsDataSourceLive
        } else {
            productsDataSourceMock
        }
}
