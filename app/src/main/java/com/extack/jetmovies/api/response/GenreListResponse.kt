package com.extack.jetmovies.api.response

data class GenreListResponse(
    val genres: List<GenreResponse>
)

data class GenreResponse(
    val id: Long,
    val name: String
)