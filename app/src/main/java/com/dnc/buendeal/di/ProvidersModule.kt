package com.dnc.buendeal.di

import com.dnc.buendeal.core.providers.PreferencesProvider
import com.dnc.buendeal.core.providers.PreferencesProviderImpl
import com.dnc.buendeal.core.providers.ResourceProvider
import com.dnc.buendeal.core.providers.ResourceProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

val providersModule = module {
    single<PreferencesProvider> { PreferencesProviderImpl(get()) }
    factory<ResourceProvider> { ResourceProviderImpl(get()) }
    single<Gson>(named(GSON)) { GsonBuilder().serializeNulls().create() }
}
