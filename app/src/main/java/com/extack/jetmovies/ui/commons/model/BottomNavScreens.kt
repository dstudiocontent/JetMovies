package com.extack.jetmovies.ui.commons.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector
import com.extack.jetmovies.R

sealed class BottomNavScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object NavHomeScreen : BottomNavScreens("nav_home", R.string.home, Icons.Filled.Home)
    object NavMovieScreen : BottomNavScreens("nav_movie", R.string.movies, Icons.Filled.Email)
}