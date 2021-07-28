package com.extack.jetmovies.domain.repository

import com.extack.jetmovies.api.commons.apiCaller
import com.extack.jetmovies.api.endpoints.MoviesApi
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val moviesApi: MoviesApi
) {
    suspend fun getMovieGenres() = apiCaller {
        moviesApi.getMovieGenres()
    }
}