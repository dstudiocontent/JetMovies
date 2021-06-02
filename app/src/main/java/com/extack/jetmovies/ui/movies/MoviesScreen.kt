package com.extack.jetmovies.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.extack.jetmovies.domain.model.Movie
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MoviesScreen(
    scrollState: LazyListState = rememberLazyListState(),
    onClick: (Long) -> Unit
) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val regionalMovies: List<Movie> by viewModel.regionalMoviesLiveData.observeAsState(emptyList())

    Column {
        LazyRow(state = scrollState) {
            itemsIndexed(regionalMovies) { _, item ->
                Image(
                    painter = rememberCoilPainter(request = item.imagePath, fadeIn = true),
                    contentDescription = "Movie Image",
                    modifier = Modifier
                        .width(140.dp)
                        .padding(8.dp)
                        .aspectRatio(.66F)
                        .clickable { onClick(item.id) }
                )
            }
        }
    }
}