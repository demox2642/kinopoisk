package com.demox.kinopoisk.di

import com.demox.main.usecase.GetMovieListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetMovieListUseCase(get()) }
}
