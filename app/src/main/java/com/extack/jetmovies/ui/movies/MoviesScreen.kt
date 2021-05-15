package com.extack.jetmovies.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logInfo
import com.google.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MoviesScreen(
    scrollState : LazyListState = rememberLazyListState(),
    coroutineContext : CoroutineScope= rememberCoroutineScope()
) {
    val viewModel: MoviesViewModel = hiltNavGraphViewModel()

    val regionalMovies : List<Movie> by viewModel.regionalMoviesLiveData.observeAsState(emptyList())
    Column {
        LazyRow(state = scrollState) {
            viewModel.savePosition(scrollState.firstVisibleItemIndex)
            coroutineContext.launch {
                logInfo("Scrolling")
                scrollState.scrollToItem(scrollState.firstVisibleItemIndex)
            }
            itemsIndexed(regionalMovies){index, item ->
                CoilImage(data = item.imagePath,contentDescription = "Movie Image",fadeIn = true,modifier = Modifier
                    .width(140.dp)
                    .padding(8.dp)
                    .aspectRatio(.66F))
            }
        }
    }
}