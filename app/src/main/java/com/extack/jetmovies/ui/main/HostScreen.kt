package com.extack.jetmovies.ui.main

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.ui.commons.model.BottomNavScreens
import com.extack.jetmovies.ui.home.HomeScreen
import com.extack.jetmovies.ui.home.HomeViewModel
import com.extack.jetmovies.ui.movie_detail.MovieScreen
import com.extack.jetmovies.ui.movies.MoviesScreen

@Composable
fun HostScreen(navController: NavHostController) {
    val bottomNavItems = listOf(BottomNavScreens.NavHomeScreen, BottomNavScreens.NavMovieScreen)

    Scaffold(
        bottomBar = {
            val currentRoute: String = currentRoute(navController = navController) ?: ""
            if (currentRoute == BottomNavScreens.NavHomeScreen.route ||
                currentRoute == BottomNavScreens.NavMovieScreen.route
            ) {
                BottomNav(navController = navController, items = bottomNavItems)
            }
        }
    ) {
        NavConfig(navController)
    }
}

@Composable
fun BottomNav(navController: NavHostController, items: List<BottomNavScreens>) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.route) },
                label = { Text(stringResource(id = screen.resourceId)) },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                })
        }
    }
}

@Composable
fun NavConfig(navController: NavHostController) {

    val viewModel: HomeViewModel = hiltNavGraphViewModel()

    val popularListState = rememberLazyListState()
    val popularMovies: List<Movie> by viewModel.popularMoviesLiveData.observeAsState(emptyList())

    NavHost(
        navController = navController,
        startDestination = "home_tab"
    ) {
        navigation(startDestination = BottomNavScreens.NavHomeScreen.route, "home_tab") {
            composable(BottomNavScreens.NavHomeScreen.route) {
                HomeScreen(navController, popularListState, popularMovies)
            }
            composable(
                "movie/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) { backstackEntry ->
                MovieScreen(backstackEntry.arguments?.getLong("id") ?: 0)
            }
        }

        composable(BottomNavScreens.NavMovieScreen.route) {
            MoviesScreen()
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}