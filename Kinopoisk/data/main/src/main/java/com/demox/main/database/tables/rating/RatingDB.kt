package com.demox.main.database.tables.rating

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = RatingContract.TABLE_NAME
)
data class RatingDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = RatingContract.Colums.ID)
    val rating_id: String,
    @ColumnInfo(name = RatingContract.Colums.AWAIT)
    val await: Double,
    @ColumnInfo(name = RatingContract.Colums.CRITICS)
    val filmCritics: Double,
    @ColumnInfo(name = RatingContract.Colums.IMDB)
    val imdb: Double,
    @ColumnInfo(name = RatingContract.Colums.KP)
    val kp: Double,
    @ColumnInfo(name = RatingContract.Colums.RUS_CRITICS)
    val russianFilmCritics: Double
)
