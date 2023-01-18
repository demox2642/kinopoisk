package com.demox.main.api

import com.demox.main.models.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {

    @GET("movie?sortField=votes.imdb&sortType=-1&sortField=year&sortType=1&field=name")
    suspend fun getMovieList(
        @Query("search") search: String,
        @Query("field") typeYear: String?,
        @Query("search") yearName: String?,
        @Query("field") typeFilm: String?,
        @Query("search") filmName: String?,
        @Query("token") token: String,
        @Query("page") currentPage: Int

    ): Response<ServerResponse>
}
