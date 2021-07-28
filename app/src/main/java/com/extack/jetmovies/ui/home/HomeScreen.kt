package com.extack.jetmovies.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.extack.jetmovies.ui.commons.components.LargeMovieComponent
import com.extack.jetmovies.ui.commons.components.MainToolbarComponent

@Composable
fun HomeScreen(
    scrollState: LazyListState = rememberLazyListState(),
    onClick: (Long) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val popularMovies = viewModel.movies.collectAsLazyPagingItems()
    Column {
        MainToolbarComponent()
        LazyColumn(state = scrollState, contentPadding = PaddingValues(0.dp, 6.dp)) {
            items(popularMovies) { movieResponse ->
                movieResponse?.let { LargeMovieComponent(movie = movieResponse, onClick = onClick) }
            }
        }
    }
}

/*
@Preview
@Composable
fun PreviewHomeScreen(){
    HomeScreen(onClick = {})
}
*/
