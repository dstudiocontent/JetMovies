package com.extack.jetmovies.ui.commons.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.extack.jetmovies.domain.model.Movie

@Composable
fun LargeMovieComponent(movie: Movie, onClick: (Long) -> Unit) {
    Box(
        modifier = Modifier
            .padding(12.dp, 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { movie.id.let(onClick) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.77F)
        ) {
            val (image, title) = createRefs()
            Image(
                painter = rememberImagePainter(
                    data = movie.imagePath,
                    builder = { crossfade(true) }
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {},
                contentDescription = "Movie Image",
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0, 0, 0, 150))
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }) {
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(16.dp, 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}