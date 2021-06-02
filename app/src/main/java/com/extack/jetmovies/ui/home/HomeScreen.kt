package com.extack.jetmovies.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.extack.jetmovies.api.response.MovieResponse
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.ui.commons.components.LargeMovieComponent

@Composable
fun HomeScreen(
    scrollState: LazyListState = rememberLazyListState(),
    onClick: (Long) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val popularMovies = viewModel.movies.collectAsLazyPagingItems()
    Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 56.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(30, 39, 44))
        ) {
        }
        LazyColumn(state = scrollState, contentPadding = PaddingValues(0.dp, 6.dp)) {
            items(popularMovies) { movieResponse ->
                movieResponse?.let { LargeMovieComponent(movie = movieResponse, onClick) }
            }
        }
    }
}