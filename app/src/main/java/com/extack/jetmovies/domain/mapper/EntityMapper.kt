package com.extack.jetmovies.domain.mapper

import com.extack.jetmovies.api.commons.BACKDROP_BASE_URL
import com.extack.jetmovies.api.commons.POSTER_BASE_URL
import com.extack.jetmovies.api.response.GenreResponse
import com.extack.jetmovies.api.response.MovieResponse
import com.extack.jetmovies.domain.model.Genre
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Movie

fun List<MovieResponse>.toMovieList(imageType: ImageType): List<Movie> {
    return this.map {
        it.toMovie(imageType)
    }
}

fun MovieResponse.toMovie(imageType: ImageType): Movie {
    val imageUrl = when (imageType) {
        ImageType.POSTER -> "$POSTER_BASE_URL$posterPath"
        ImageType.BACKDROP -> "$BACKDROP_BASE_URL$backdropPath"
    }
    return Movie(id, title, originalTitle, imageUrl)
}

fun GenreResponse.toGenre(): Genre {
    return Genre(id, name)
}

