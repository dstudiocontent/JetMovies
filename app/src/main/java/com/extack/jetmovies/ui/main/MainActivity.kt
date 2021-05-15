package com.extack.jetmovies.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.extack.jetmovies.ui.home.HomeScreen
import com.extack.jetmovies.ui.movie_detail.MovieScreen
import com.extack.jetmovies.ui.theme.JetMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            JetMoviesTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    HostScreen(navController = navController)
                }
            }
        }
    }
}