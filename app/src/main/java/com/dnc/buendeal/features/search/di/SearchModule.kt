package com.dnc.buendeal.features.search.di

import com.dnc.buendeal.di.MOCKED_SOURCE
import com.dnc.buendeal.di.NETWORK_SOURCE
import com.dnc.buendeal.features.search.data.datasource.SearchDataSource
import com.dnc.buendeal.features.search.data.datasource.SearchLiveDataSourceImpl
import com.dnc.buendeal.features.search.data.datasource.SearchMockDataSourceImpl
import com.dnc.buendeal.features.search.domain.SearchInteractor
import com.dnc.buendeal.features.search.domain.SearchInteractorImpl
import com.dnc.buendeal.features.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single<SearchDataSource>(NETWORK_SOURCE) { SearchLiveDataSourceImpl(get(), get()) }
    single<SearchDataSource>(MOCKED_SOURCE) { SearchMockDataSourceImpl() }

    factory<SearchInteractor> {
        SearchInteractorImpl(
            get(MOCKED_SOURCE),
            get(NETWORK_SOURCE),
            get(MOCKED_SOURCE),
            get(NETWORK_SOURCE)
        )
    }

    viewModel { SearchViewModel(get(), get()) }
}
