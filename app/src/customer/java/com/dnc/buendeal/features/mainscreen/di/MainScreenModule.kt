package com.dnc.buendeal.features.mainscreen.di

import com.dnc.buendeal.di.MOCKED_SOURCE
import com.dnc.buendeal.di.NETWORK_SOURCE
import com.dnc.buendeal.features.mainscreen.domain.MainScreenInteractor
import com.dnc.buendeal.features.mainscreen.domain.MainScreenInteractorImpl
import com.dnc.buendeal.features.mainscreen.ui.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {

    factory<MainScreenInteractor> {
        MainScreenInteractorImpl(
            get(NETWORK_SOURCE),
            get(MOCKED_SOURCE),
            get(NETWORK_SOURCE),
            get(MOCKED_SOURCE),
            get()
        )
    }
    viewModel { MainScreenViewModel(get()) }
}
