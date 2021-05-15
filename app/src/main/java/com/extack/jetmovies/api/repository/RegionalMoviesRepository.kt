package com.extack.jetmovies.api.repository

import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.commons.apiCaller
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import javax.inject.Inject

class RegionalMoviesRepository @Inject constructor(
    private val regionalMoviesApi: RegionalMoviesApi
) {
    suspend fun getRegionalMovies(
        region: String,
        releaseYear: Int = LocalDate.now().year,
        originalLang: String
    ): Resource<RegionalMovieResponse> {
        return apiCaller {
            regionalMoviesApi.getRegionalMovies(
                region = region,
                releaseYear = releaseYear,
                originalLang = originalLang
            )
        }
    }
}

interface RegionalMoviesApi {
    @GET("discover/movie")
    suspend fun getRegionalMovies(
        @Query("region") region: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("with_original_language") originalLang: String
    ): RegionalMovieResponse
}

data class RegionalMovieResponse(
    val page: Int,
    val results: List<RegionalMovie>
)

data class RegionalMovie(
    val id: Long,
    val title: String,
    val poster_path: String
)