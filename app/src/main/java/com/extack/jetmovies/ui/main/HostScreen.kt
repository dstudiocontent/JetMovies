package com.extack.jetmovies.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import com.extack.jetmovies.ui.commons.components.ToolbarComponent
import com.extack.jetmovies.ui.commons.model.BottomNavScreens
import com.extack.jetmovies.ui.home.HomeScreen
import com.extack.jetmovies.ui.movie_detail.MovieScreen
import com.extack.jetmovies.ui.movies.MoviesScreen
import com.extack.jetmovies.ui.programs.ProgramsScreen
import com.extack.jetmovies.ui.regional_movies.RegionalMoviesScreen

@ExperimentalFoundationApi
@Composable
fun HostScreen(navController: NavHostController) {

    val bottomNavItems = listOf(
        BottomNavScreens.NavHomeScreen,
        BottomNavScreens.NavMovieScreen,
        BottomNavScreens.NavTvScreen
    )

    Scaffold(
        bottomBar = {
            val currentRoute: String = currentRoute(navController = navController) ?: ""
            bottomNavItems.find { it.route == currentRoute }?.let {
                BottomNav(navController = navController, items = bottomNavItems)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, padding.calculateBottomPadding())
        ) {
            ToolbarComponent()
            NavConfig(navController)
        }
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
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavConfig(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavScreens.NavHomeScreen.route
    ) {
        composable(BottomNavScreens.NavHomeScreen.route) {
            HomeScreen { id ->
                toMovieDetail(navController = navController, id = id)
            }
        }
        composable(BottomNavScreens.NavMovieScreen.route) {
            MoviesScreen(onMovieClick = { id ->
                toMovieDetail(navController = navController, id = id)
            }, onViewAllClick = { isoValue ->
                navController.navigate("regional/${isoValue}") {
                    popUpTo("regional") {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        }
        composable(BottomNavScreens.NavTvScreen.route) {
            ProgramsScreen()
        }

        composable(
            "movie/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backstackEntry ->
            MovieScreen(backstackEntry.arguments?.getLong("id") ?: 0)
        }

        composable(
            "regional/{isoValue}",
            arguments = listOf(navArgument("isoValue") { type = NavType.StringType })
        ) { backstackEntry ->
            RegionalMoviesScreen(backstackEntry.arguments?.getString("isoValue") ?: "")
        }

    }
}

fun toMovieDetail(navController: NavHostController, id: Long) {
    navController.navigate("movie/${id}") {
        popUpTo("movie") {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}