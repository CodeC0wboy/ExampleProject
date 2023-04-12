package com.dnc.buendeal.di

import com.dnc.buendeal.MainViewModel
import com.dnc.buendeal.core.core.data.datasource.*
import com.dnc.buendeal.di.scope.AUTHORIZED_SCOPE_NAME
import com.dnc.buendeal.di.scope.EMPTY_SCOPE_NAME
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    scope(EMPTY_SCOPE_NAME) {
    }

    scope(AUTHORIZED_SCOPE_NAME) {
    }
    viewModel { MainViewModel(get(), get()) }


    single<PromotionsBannerDataSource>(NETWORK_SOURCE) { PromotionsBannerLiveDataSourceImpl(get()) }
    single<PromotionsBannerDataSource>(MOCKED_SOURCE) { PromotionsBannerMockDataSourceImpl() }

    single<ProductsDataSource>(NETWORK_SOURCE) { ProductsLiveDataSourceImpl(get(), get()) }
    single<ProductsDataSource>(MOCKED_SOURCE) { ProductsMockDataSourceImpl() }


}
