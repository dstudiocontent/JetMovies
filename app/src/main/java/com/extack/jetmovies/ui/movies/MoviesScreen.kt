package com.extack.jetmovies.ui.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.extack.jetmovies.domain.model.Language
import com.extack.jetmovies.domain.model.RegionalMovie
import com.extack.jetmovies.extensions.logInfo
import com.extack.jetmovies.ui.commons.components.ListMovieComponent

@Composable
fun MoviesScreen(
    onMovieClick: (Long) -> Unit,
    onViewAllClick: (Language) -> Unit,
) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val regionalMovies: List<RegionalMovie> by viewModel.regionalMoviesStateFlow.collectAsState()

    LazyColumn {
        items(regionalMovies) { regionalMovie ->
            logInfo(regionalMovies.toString())
            ListMovieComponent(
                regionalMovie = regionalMovie,
                scrollState = rememberLazyListState(),
                onMovieClick = onMovieClick,
                onViewAllClick = onViewAllClick
            )
        }
    }
}