package com.dnc.buendeal.di

import com.dnc.buendeal.base.EmptyViewModel
import com.dnc.buendeal.core.network.ConnectionStatusListener
import com.dnc.buendeal.core.network.ConnectionStatusListenerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val utilsModule = module {
    single<ConnectionStatusListener> { ConnectionStatusListenerImpl(androidContext()) }
    viewModel { EmptyViewModel() }
}
