package com.extack.jetmovies.api.endpoints

import com.extack.jetmovies.api.response.GenreListResponse
import com.extack.jetmovies.api.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int): MovieListResponse

    @GET("discover/movie")
    suspend fun getRegionalMovies(
        @Query("with_original_language") originalLang: String,
        @Query("page") pageNumber: Int = 1,
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("release_date.lte") today: String = LocalDate.now().toString()
    ): MovieListResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreListResponse
}