package com.extack.jetmovies.domain.model

data class RegionalMovie(
    val language: Language,
    val movies : List<Movie>
)