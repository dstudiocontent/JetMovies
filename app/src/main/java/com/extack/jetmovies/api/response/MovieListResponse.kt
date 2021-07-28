package com.extack.jetmovies.api.response

import com.squareup.moshi.Json

data class MovieListResponse(
    val page: Int,
    val results: List<MovieResponse>
)

data class MovieResponse(
    val id: Long,
    val title: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "poster_path")
    val posterPath: String
)