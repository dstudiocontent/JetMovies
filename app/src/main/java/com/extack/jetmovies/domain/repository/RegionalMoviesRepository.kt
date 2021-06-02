package com.extack.jetmovies.domain.repository

import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.commons.apiCaller
import com.extack.jetmovies.api.endpoints.MoviesApi
import com.extack.jetmovies.api.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import javax.inject.Inject

class RegionalMoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi
) {
    suspend fun getRegionalMovies(
        region: String,
        releaseYear: Int = LocalDate.now().year,
        originalLang: String
    ): Resource<MovieListResponse> {
        return apiCaller {
            moviesApi.getRegionalMovies(region, releaseYear, originalLang)
        }
    }
}