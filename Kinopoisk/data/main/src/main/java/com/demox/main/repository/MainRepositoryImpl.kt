package com.demox.main.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demox.main.api.KinopoiskApi
import com.demox.main.database.MoviesDB
import com.demox.main.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val api: KinopoiskApi,
    private val dataBase: MoviesDB
) : MainRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    override suspend fun getMovieList(query: String, year: String, type: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(api, dataBase, query, year, type) }
        ).flow
    }
}
