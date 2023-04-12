package com.dnc.buendeal.features.search.data.datasource

import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.core.data.response.ProductMockResponse
import com.dnc.buendeal.core.core.data.response.RecentSearchResponse
import com.dnc.buendeal.core.core.data.response.filter.FilterSortResponse
import com.dnc.buendeal.core.core.data.response.search.DataResponse
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.*
import com.dnc.buendeal.core.network.ApiSearch
import com.dnc.buendeal.core.providers.PreferencesProvider
import com.dnc.buendeal.features.search.data.response.CategoryFilterResponse
import com.dnc.buendeal.features.search.data.response.CategorySearchResponse
import com.dnc.buendeal.features.search.domain.model.CategoryFilter
import com.dnc.buendeal.features.search.domain.model.CategorySearch
import com.dnc.buendeal.features.search.domain.model.domain

interface SearchDataSource {
    suspend fun getRecent(): List<RecentSearch>
    suspend fun saveSearch(query: RecentSearch)
    suspend fun getCategories(): List<CategorySearch>
    suspend fun getSortBy(): FilterSort
    suspend fun getCategoryFilters(): List<CategoryFilter>
    suspend fun getProducts(query: String): List<Product>
}

class SearchLiveDataSourceImpl(
    val searchApi: ApiSearch,
    val preferencesProvider: PreferencesProvider
) : SearchDataSource {

    var data: DataResponse? = null

    override suspend fun getRecent(): List<RecentSearch> = preferencesProvider.getRecentSearch()

    override suspend fun saveSearch(query: RecentSearch) = preferencesProvider.saveSearch(query)

    override suspend fun getCategories(): List<CategorySearch> {
//        val list = arrayListOf<CategorySearch>()
//        data?.catalogItems?.forEach {
//            list.add(CategorySearch(it.label))
//        }
        return arrayListOf()
    }

    override suspend fun getSortBy() = FilterSortResponse(
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

    override suspend fun getCategoryFilters(): List<CategoryFilter> {
        val list = arrayListOf<CategoryFilter>()

        list.add(CategoryFilter("0", "All categories"))
        data?.catalogItems?.forEach {
            list.add(CategoryFilter(it.id.toString(), it.label))
        }
        return list
    }

    override suspend fun getProducts(query: String): List<Product> {
        //api request
        return arrayListOf()
    }
}

class SearchMockDataSourceImpl : SearchDataSource {
    override suspend fun getRecent(): List<RecentSearch> = listOf(
        RecentSearchResponse("macbook laptop"),
        RecentSearchResponse("HP laptop"),
        RecentSearchResponse("ASUS laptop"),
        RecentSearchResponse("macbook laptop"),
        RecentSearchResponse("laptop computer"),
        RecentSearchResponse("laptop sale")
    ).map {
        it.domain()
    }

    override suspend fun saveSearch(query: RecentSearch) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<CategorySearch> = listOf(
        CategorySearchResponse("Laptops"),
        CategorySearchResponse("Computers parts & components"),
        CategorySearchResponse("Computers accessories")
    ).map {
        it.domain()
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

    override suspend fun getCategoryFilters(): List<CategoryFilter> = listOf(
        CategoryFilterResponse("1", "All categories"),
        CategoryFilterResponse("2", "Laptops"),
        CategoryFilterResponse("3", "Computers parts & components"),
        CategoryFilterResponse("4", "Computers accessories")
    ).map { it.domain() }

    override suspend fun getProducts(query: String): List<Product> {
        return listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
            ProductMockResponse(
                id = "4",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
            ProductMockResponse(
                id = "5",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
            ProductMockResponse(
                id = "6",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = null
            ),
        ).map { it.domain() }
    }
}
