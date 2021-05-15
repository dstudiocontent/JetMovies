package com.extack.jetmovies.api.repository

import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.commons.apiCaller
import com.squareup.moshi.Json
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
    val id: Long,
    val title : String,
    @field:Json(name = "backdrop_path")
    val poster_path : String
)