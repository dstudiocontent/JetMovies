package com.extack.jetmovies.ui.regional_movies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.extack.jetmovies.extensions.items
import com.extack.jetmovies.ui.commons.components.RegionalMoviesToolbar
import com.extack.jetmovies.ui.commons.components.SmallMovieComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun RegionalMoviesScreen(
    isoValue: String,
    title: String,
    listState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    moveUp: () -> Unit
) {
    val viewModel = hiltViewModel<RegionalMoviesViewModel>()
    val movies = viewModel.getRegionalMovies(isoValue).collectAsLazyPagingItems()
    //val genres = viewModel.genreStateFlow.collectAsState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    /*BackHandler {
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded)
            coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
        else moveUp()
    }*/


    /*BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                LazyColumn {
                    items(genres.value) { genre ->
                        Row {
                            Checkbox(checked = genre.isChecked, onCheckedChange = {
                                logInfo(it.toString())
                                viewModel.onGenreSelected(genre.id, it)
                            })
                            Text(text = genre.name)
                        }

                    }
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {}*/
    Column {
        RegionalMoviesToolbar(title = title) {
            coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.apply {
                    if (isExpanded) collapse()
                    else expand()
                }
            }
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3), state = listState
        ) {
            items(movies) { movie ->
                SmallMovieComponent(item = movie ?: return@items) {

                }
            }
        }
    }
}