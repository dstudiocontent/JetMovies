package com.extack.jetmovies.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logInfo

@Composable
fun HomeScreen(
    navController: NavHostController,
    popularListState: LazyListState,
    popularMovies : List<Movie>
) {
    logInfo("Recomposition")

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(30, 39, 44))
        ) {

        }
        HomeMovieListComponent(
            movies = popularMovies,
            state = popularListState,
        ) { id ->
            navController.navigate("movie/${id}")
        }
    }

}