package com.demox.main.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.demox.main.api.KinopoiskApi
import com.demox.main.database.MoviesDB
import com.demox.main.database.tables.movie.MovieDB
import com.demox.main.database.tables.rating.RatingDB
import com.demox.main.database.tables.votes.VotesDB
import com.demox.main.models.*

class MoviePagingSource(
    private val api: KinopoiskApi,
    private val dataBase: MoviesDB,
    private val query: String,
    private val year: String,
    private val type: String
) : PagingSource<Int, Movie>() {
    companion object {
        const val token = "ZK08G1W-3CC47AV-M2DYP9D-7HNX92K"
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, Movie> {
        val currentPage = params.key ?: 1
        // параметры для динамического uri
        var typeYear: String? = null
        var yearName: String? = null
        var typeFilm: String? = null
        var filmName: String? = null
        if (type.isNotEmpty()) {
            typeFilm = "typeNumber"
            filmName = type
        }

        if (year.isNotEmpty()) {
            typeYear = "year"
            yearName = year
        }
        var response = emptyList<MovieResponse>()
        try {
            response =
                api.getMovieList(query, typeYear, yearName, typeFilm, filmName, token, currentPage).body()?.docs!!
        } catch (e: Exception) {
            Log.e("PagingSoursr", "Error=$e")
        }

        val endOfPaginationReached = response.isNullOrEmpty()
        return if (response.isNullOrEmpty()) {
            // если ответ с сервера пустой или с ошибкой, то загружаем данные из БД
            LoadResult.Page(
                data = dataBase.movieDao().selectAllMovie(query, type.movieTypeForDataBaseCall(), year, params.loadSize, currentPage).map {
                    Movie(
                        id = it.movie.id,
                        alternativeName = it.movie.alternativeName,
                        description = it.movie.description,
                        poster = it.movie.poster,
                        name = it.movie.name,
                        rating = Rating(
                            _id = it.rating.rating_id,
                            await = it.rating.await,
                            filmCritics = it.rating.filmCritics,
                            imdb = it.rating.imdb,
                            kp = it.rating.kp,
                            russianFilmCritics = it.rating.russianFilmCritics
                        ),
                        shortDescription = it.movie.shortDescription,
                        type = it.movie.type,
                        votes = Votes(
                            _id = it.votes.votes_id,
                            await = it.votes.await,
                            filmCritics = it.votes.filmCritics,
                            imdb = it.votes.imdb,
                            kp = it.votes.kp,
                            russianFilmCritics = it.votes.russianFilmCritics
                        ),
                        year = it.movie.year
                    )
                },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        } else {
            // если ответ с сервера корректный то данные пишем в БД и передаем в репозиторий
            writeToDB(response)
            LoadResult.Page(
                data = response.map { it.mapToMovie() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        }
    }

    private suspend fun writeToDB(movieResponse: List<MovieResponse>) {
        // записываем данные в БД
        dataBase.withTransaction {
            dataBase.votesDao().insertVotes(
                movieResponse.map {
                    VotesDB(
                        votes_id = it.votes._id,
                        await = it.votes.await,
                        filmCritics = it.votes.filmCritics,
                        imdb = it.votes.imdb,
                        kp = it.votes.kp,
                        russianFilmCritics = it.votes.russianFilmCritics
                    )
                }
            )

            dataBase.ratingDao().insertRating(
                movieResponse.map {
                    RatingDB(
                        rating_id = it.rating._id,
                        await = it.rating.await,
                        filmCritics = it.rating.filmCritics,
                        imdb = it.rating.imdb,
                        kp = it.rating.kp,
                        russianFilmCritics = it.rating.russianFilmCritics
                    )
                }
            )

            dataBase.movieDao().insertMovie(
                movieResponse.map {
                    MovieDB(
                        id = it.id,
                        alternativeName = it.alternativeName,
                        description = it.description,
                        poster = it.poster?.url,
                        name = it.name,
                        rating = it.rating._id,
                        shortDescription = it.shortDescription,
                        type = it.type,
                        votes = it.votes._id,
                        year = it.year
                    )
                }

            )
        }
    }
}
