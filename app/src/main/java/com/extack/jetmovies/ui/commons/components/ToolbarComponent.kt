package com.extack.jetmovies.ui.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
fun ToolbarComponent(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(30, 39, 44))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "JetMovies",
            fontFamily = qs,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1F),
            fontSize = 22.sp,
            color = Color.Red
        )
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
        }
    }
}