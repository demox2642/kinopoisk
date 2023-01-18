package com.demox.main.database.tables.rating

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(votes: List<RatingDB>)

    @Query("SELECT * FROM ${RatingContract.TABLE_NAME}")
    suspend fun selectAllRating(): List<RatingDB>
}
