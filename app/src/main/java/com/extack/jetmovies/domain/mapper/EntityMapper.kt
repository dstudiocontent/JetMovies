package com.extack.jetmovies.domain.mapper

import com.extack.jetmovies.api.commons.BACKDROP_BASE_URL
import com.extack.jetmovies.api.commons.POSTER_BASE_URL
import com.extack.jetmovies.api.repository.PopularMovie
import com.extack.jetmovies.api.repository.RegionalMovie
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logInfo

fun PopularMovie.toMovie() : Movie {
        return Movie(
        id = this.id,
        title = title,
        imagePath = "$BACKDROP_BASE_URL$poster_path"
    )
}

fun RegionalMovie.toMovie() : Movie {
    return Movie(
        id = this.id,
        title = title,
        imagePath = "$POSTER_BASE_URL$poster_path"
    )
}