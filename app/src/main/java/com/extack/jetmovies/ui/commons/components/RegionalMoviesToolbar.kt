package com.extack.jetmovies.ui.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.extack.jetmovies.ui.theme.qs

@Composable
fun RegionalMoviesToolbar(
    title: String,
    onFilterClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(30, 39, 44))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = qs,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1F),
            fontSize = 18.sp,
            color = Color.White
        )
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
        }
        IconButton(onClick = { onFilterClicked() }) {
            Icon(imageVector = Icons.Rounded.FilterList, contentDescription = "Filter Icon")
        }
    }
}