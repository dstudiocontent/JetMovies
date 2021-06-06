package com.extack.jetmovies.ui.regional_movies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.ui.commons.components.SmallMovieComponent

@ExperimentalFoundationApi
@Composable
fun RegionalMoviesScreen(isoValue: String, listState: LazyListState = rememberLazyListState()) {
    val viewModel = hiltViewModel<RegionalMoviesViewModel>()
    val movies = viewModel.getRegionalMovies(isoValue).collectAsLazyPagingItems()
    Column {
        LazyVerticalGrid(cells = GridCells.Fixed(3), state = listState) {
            items(movies.itemCount) { index ->
                val movie = movies.getAsState(index = index)
                SmallMovieComponent(item = movie.value ?: return@items) {

                }
            }

        }
    }
}