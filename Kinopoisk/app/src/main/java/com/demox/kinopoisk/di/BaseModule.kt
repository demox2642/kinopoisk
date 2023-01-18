package com.demox.kinopoisk.di

import android.app.Application
import androidx.room.Room
import com.demox.main.database.MoviesDB
import com.demox.main.database.tables.movie.MovieDAO
import com.demox.main.database.tables.rating.RatingDAO
import com.demox.main.database.tables.votes.VotesDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val uiBaseModule = module {
    fun provideDatabase(application: Application): MoviesDB {
        return Room.databaseBuilder(application, MoviesDB::class.java, "movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMovieDao(database: MoviesDB): MovieDAO {
        return database.movieDao()
    }

    fun provideVotesDao(database: MoviesDB): VotesDAO {
        return database.votesDao()
    }
    fun provideRatingDao(database: MoviesDB): RatingDAO {
        return database.ratingDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMovieDao(get()) }
    single { provideVotesDao(get()) }
    single { provideRatingDao(get()) }
}
