package com.dnc.buendeal.core.core.data.datasource.database

import com.dnc.buendeal.core.core.data.response.ProductDto

interface RecentlyViewedProductsDataSource {
    suspend fun addProductsToRecentlyViewed(product: ProductDto)
    suspend fun getRecentlyViewedProducts(): List<ProductDto>
}

class RecentlyViewedProductsDataSourceImpl(private val dao: ProductDao) :
    RecentlyViewedProductsDataSource {
    override suspend fun addProductsToRecentlyViewed(product: ProductDto) = dao.insertAll(product)

    override suspend fun getRecentlyViewedProducts(): List<ProductDto> = dao.getAll()
}
