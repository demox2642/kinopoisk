package com.demox.main.repository

import androidx.paging.PagingData
import com.demox.main.models.Movie
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMovieList(query: String, year: String, type: String): Flow<PagingData<Movie>>
}
