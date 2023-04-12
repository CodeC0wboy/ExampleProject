package com.dnc.buendeal.core.core.data.datasource

import com.dnc.buendeal.core.core.data.response.BannerResponse
import com.dnc.buendeal.core.core.domain.model.Banner
import com.dnc.buendeal.core.core.domain.model.domain
import com.dnc.buendeal.core.network.ApiService
import java.util.concurrent.atomic.AtomicLong

interface PromotionsBannerDataSource {

    suspend fun getBanners(): List<Banner>
    suspend fun getBannersByCategory(catalogId: String): List<Banner>
}

class PromotionsBannerLiveDataSourceImpl(private val apiService: ApiService) :
    PromotionsBannerDataSource {

    override suspend fun getBanners(): List<Banner> = arrayListOf()

    override suspend fun getBannersByCategory(catalogId: String): List<Banner> =
        arrayListOf()
}

class PromotionsBannerMockDataSourceImpl : PromotionsBannerDataSource {

    var idLong = AtomicLong(0)

    override suspend fun getBanners(): List<Banner> = listOf(
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
    ).map { it.domain() }

    override suspend fun getBannersByCategory(catalogId: String): List<Banner> = listOf(
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg"
        ),
        BannerResponse(
            idLong.incrementAndGet(),
            null,
            "https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg"
        ),
    ).map { it.domain() }
}
