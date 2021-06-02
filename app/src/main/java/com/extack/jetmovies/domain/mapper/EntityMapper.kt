package com.extack.jetmovies.domain.mapper

import com.extack.jetmovies.api.commons.BACKDROP_BASE_URL
import com.extack.jetmovies.api.commons.POSTER_BASE_URL
import com.extack.jetmovies.api.response.MovieResponse
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Movie

fun MovieResponse.toMovie(imageType: ImageType): Movie {
    val imageUrl = when (imageType) {
        ImageType.POSTER -> "$POSTER_BASE_URL$posterPath"
        ImageType.BACKDROP -> "$BACKDROP_BASE_URL$backdropPath"
    }
    return Movie(this.id, title, imageUrl)
}