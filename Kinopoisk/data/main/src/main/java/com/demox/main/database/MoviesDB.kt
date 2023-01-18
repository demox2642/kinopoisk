package com.demox.main.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demox.main.database.MoviesDB.Companion.DB_VERSION
import com.demox.main.database.tables.movie.MovieDAO
import com.demox.main.database.tables.movie.MovieDB
import com.demox.main.database.tables.rating.RatingDAO
import com.demox.main.database.tables.rating.RatingDB
import com.demox.main.database.tables.votes.VotesDAO
import com.demox.main.database.tables.votes.VotesDB

@Database(
    entities = [
        MovieDB::class,
        VotesDB::class,
        RatingDB::class
    ],
    version = DB_VERSION
)

abstract class MoviesDB : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
    abstract fun votesDao(): VotesDAO
    abstract fun ratingDao(): RatingDAO

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "MovieDB"
    }
}
