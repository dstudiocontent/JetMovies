package com.extack.jetmovies.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import com.extack.jetmovies.ui.commons.model.BottomNavScreens
import com.extack.jetmovies.ui.home.HomeScreen
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
            MoviesScreen { id ->
                toMovieDetail(navController = navController, id = id)
            }
        }

        composable(
            "movie/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backstackEntry ->
            MovieScreen(backstackEntry.arguments?.getLong("id") ?: 0)
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