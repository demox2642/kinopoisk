package com.demox.main.database.tables.votes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = VotesContract.TABLE_NAME
)
data class VotesDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = VotesContract.Colums.ID)
    val votes_id: String,
    @ColumnInfo(name = VotesContract.Colums.AWAIT)
    val await: Double,
    @ColumnInfo(name = VotesContract.Colums.CRITICS)
    val filmCritics: Double,
    @ColumnInfo(name = VotesContract.Colums.IMDB)
    val imdb: Double,
    @ColumnInfo(name = VotesContract.Colums.KP)
    val kp: Double,
    @ColumnInfo(name = VotesContract.Colums.RUS_CRITICS)
    val russianFilmCritics: Double
)
