package com.demox.main.usecase

import androidx.paging.PagingData
import com.demox.main.models.Movie
import com.demox.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(private val repository: MainRepository) {

    suspend fun getMovieList(query: String, year: String, type: String): Flow<PagingData<Movie>> = repository.getMovieList(query, year, type)
}
