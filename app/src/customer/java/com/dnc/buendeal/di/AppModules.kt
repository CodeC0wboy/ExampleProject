package com.dnc.buendeal.di

import com.dnc.buendeal.features.dashboard.di.dashboardModule
import com.dnc.buendeal.features.mainscreen.di.mainScreenModule

import com.dnc.buendeal.features.search.di.searchModule


val applicationModules = listOf(
    providersModule,
    apiModule,
    utilsModule,
    coreModule,
    dashboardModule,
    mainScreenModule,
    searchModule,
)
