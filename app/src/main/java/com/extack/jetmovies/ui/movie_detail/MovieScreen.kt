package com.extack.jetmovies.ui.movie_detail

import androidx.compose.runtime.Composable
import com.extack.jetmovies.extensions.logInfo

@Composable
fun MovieScreen(movieId : Long){
    logInfo("$movieId")
}