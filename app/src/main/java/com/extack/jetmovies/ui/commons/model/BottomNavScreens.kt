package com.extack.jetmovies.ui.commons.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.res.painterResource
import com.extack.jetmovies.R

sealed class BottomNavScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object NavHomeScreen : BottomNavScreens("nav_home", R.string.home, Icons.Rounded.Home)
    object NavMovieScreen : BottomNavScreens("nav_movie", R.string.movies, Icons.Rounded.Movie)
    object NavTvScreen : BottomNavScreens("nav_tv", R.string.tv, Icons.Rounded.Tv)
}