package com.extack.jetmovies.api

import com.extack.jetmovies.api.other.Resource
import com.extack.jetmovies.api.other.apiCaller
import retrofit2.http.GET
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val popularMoviesApi: PopularMoviesApi
) {
    suspend fun getPopularMovies(): Resource<PopularMovieResponse> {
        return apiCaller {
            popularMoviesApi.getPopularMovies()
        }
    }
}

interface PopularMoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieResponse
}

data class PopularMovieResponse(
    val page: Int,
    val results: List<PopularMovie>
)

data class PopularMovie(
    val id: Long
)