package com.extack.jetmovies.ui.home

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logInfo
import com.extack.jetmovies.ui.commons.model.ScrollDetail
import com.google.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeMovieListComponent(
    movies: List<Movie>,
    state: LazyListState,
    moveToDetail: (movieId: Long) -> Unit,
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                .clipToBounds(),
            state = state
        ) {
            itemsIndexed(movies) { _, movie ->
                Log.w("_TAG",movie.toString())
                HomeMovieComponent(movie = movie) {
                    moveToDetail(movie.id)
                }
            }
        }
    }
}

@Composable
fun HomeMovieComponent(movie: Movie, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            elevation = 12.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .clickable(onClick = onClick)
            )
            {
                val (image, title) = createRefs()
                CoilImage(
                    data = movie.imagePath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.77F)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    fadeIn = true,
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0, 150))
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }) {
                    Text(text = movie.title, modifier = Modifier.padding(16.dp, 8.dp))
                }
            }
        }
    }
}