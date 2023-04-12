package com.dnc.buendeal.core.network

import com.dnc.buendeal.core.core.data.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSearch {

    @GET("NDA")
    suspend fun getProductsByQuery(
        @Query(value = "search") search: String
    ): SearchResponse
}
