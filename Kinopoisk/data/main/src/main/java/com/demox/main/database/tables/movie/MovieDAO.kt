package com.demox.main.database.tables.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieDB>)

    @Query(
        "SELECT * FROM ${MovieContract.TABLE_NAME} WHERE ${MovieContract.Colums.NAME} LIKE '%'||:name||'%' " +
            "AND ${MovieContract.Colums.TYPE} LIKE '%'||:type||'%' " +
            "AND ${MovieContract.Colums.YEAR} LIKE '%'||:year||'%' " +
            " ORDER BY id ASC LIMIT :limit OFFSET :offset"
    )
    suspend fun selectAllMovie(name: String, type: String, year: String, limit: Int, offset: Int): List<MovieDetailDB>
}
