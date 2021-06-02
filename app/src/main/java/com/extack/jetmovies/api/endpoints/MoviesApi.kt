package com.extack.jetmovies.api.endpoints

import com.extack.jetmovies.api.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int): MovieListResponse

    @GET("discover/movie")
    suspend fun getRegionalMovies(
        @Query("region") region: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("with_original_language") originalLang: String
    ): MovieListResponse
}