package com.demox.kinopoisk.di

import com.demox.main.di.dataModule
import com.demox.main.di.networkModule

val koinModules = listOf(
    dataModule,
    uiBaseModule,
    networkModule,
    domainModule,
    presentationMain
)