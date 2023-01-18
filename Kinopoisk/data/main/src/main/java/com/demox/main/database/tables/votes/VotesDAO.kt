package com.demox.main.database.tables.votes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVotes(votes: List<VotesDB>)

    @Query("SELECT * FROM ${VotesContract.TABLE_NAME}")
    suspend fun selectAllVotes(): List<VotesDB>
}
