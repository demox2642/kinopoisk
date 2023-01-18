package com.demox.main.di

import com.demox.main.api.KinopoiskApi
import com.demox.main.database.MoviesDB
import com.demox.main.repository.MainRepository
import com.demox.main.repository.MainRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    fun provideMainRepository(api: KinopoiskApi, moviesDB: MoviesDB): MainRepository {
        return MainRepositoryImpl(api, moviesDB)
    }
    single { provideMainRepository(get(), get()) }

    fun provideKinopoiskApi(retrofit: Retrofit): KinopoiskApi {
        return retrofit.create(KinopoiskApi::class.java)
    }
    single { provideKinopoiskApi(get()) }
}
