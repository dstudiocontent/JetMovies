package com.extack.jetmovies.ui.commons.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.domain.model.RegionalMovie
import com.extack.jetmovies.ui.theme.qs
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun ListMovieComponent(
    regionalMovie: RegionalMovie,
    scrollState: LazyListState,
    onViewAllClick: (String) -> Unit,
    onMovieClick: (Long) -> Unit
) {
    Column(modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 12.dp)) {
        Row(Modifier.padding(16.dp, 0.dp)) {
            Text(
                modifier = Modifier
                    .alignByBaseline()
                    .weight(1F),
                text = regionalMovie.language.englishName,
                fontFamily = qs,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
            TextButton(modifier = Modifier.alignByBaseline(),
                onClick = { onViewAllClick(regionalMovie.language.isoValue) }) {
                Text(text = "View all", fontFamily = qs, fontWeight = FontWeight.SemiBold)
            }
        }
        LazyRow(state = scrollState, contentPadding = PaddingValues(8.dp, 0.dp)) {
            itemsIndexed(regionalMovie.movies) { _, item ->
                SmallMovieComponent(item = item, onMovieClick = onMovieClick)
            }
        }
    }
}

@Composable
fun SmallMovieComponent(item: Movie, onMovieClick: (Long) -> Unit) {
    Box(modifier = Modifier
        .width(140.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(6.dp))
        .background(Color(10, 10, 10), shape = RoundedCornerShape(6.dp))
        .aspectRatio(.66F)
        .clickable { onMovieClick(item.id) }) {

        Text(
            text = item.title,
            fontFamily = qs,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )
        Image(
            painter = rememberCoilPainter(request = item.imagePath, fadeIn = true),
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

    }

}