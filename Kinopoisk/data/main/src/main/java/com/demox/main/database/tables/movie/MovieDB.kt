package com.demox.main.database.tables.movie

import androidx.room.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demox.main.database.tables.rating.RatingContract
import com.demox.main.database.tables.rating.RatingDB
import com.demox.main.database.tables.votes.VotesContract
import com.demox.main.database.tables.votes.VotesDB

@Entity(
    tableName = MovieContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RatingDB::class,
            parentColumns = [RatingContract.Colums.ID],
            childColumns = [MovieContract.Colums.RATING_ID]
        ),
        ForeignKey(
            entity = VotesDB::class,
            parentColumns = [VotesContract.Colums.ID],
            childColumns = [MovieContract.Colums.VOTES_ID]
        )
    ]

)

data class MovieDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = MovieContract.Colums.ID)
    val id: Int,
    @ColumnInfo(name = MovieContract.Colums.ALTER_NAME)
    val alternativeName: String?,
    @ColumnInfo(name = MovieContract.Colums.DESCR)
    val description: String?,
    @ColumnInfo(name = MovieContract.Colums.POSTER)
    val poster: String?,
    @ColumnInfo(name = MovieContract.Colums.NAME)
    val name: String?,
    @ColumnInfo(name = MovieContract.Colums.RATING_ID)
    val rating: String,
    @ColumnInfo(name = MovieContract.Colums.SHORT_DESCR)
    val shortDescription: String?,
    @ColumnInfo(name = MovieContract.Colums.TYPE)
    val type: String,
    @ColumnInfo(name = MovieContract.Colums.VOTES_ID)
    val votes: String,
    @ColumnInfo(name = MovieContract.Colums.YEAR)
    val year: Int
)

data class MovieDetailDB(
    @Embedded
    val movie: MovieDB,
    @Relation(
        parentColumn = VotesContract.Colums.ID,
        entityColumn = MovieContract.Colums.VOTES_ID
    )
    var votes: VotesDB,
    @Relation(
        parentColumn = RatingContract.Colums.ID,
        entityColumn = MovieContract.Colums.RATING_ID
    )
    var rating: RatingDB
)
