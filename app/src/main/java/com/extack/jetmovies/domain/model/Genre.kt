package com.extack.jetmovies.domain.model

data class Genre(
    val id: Long,
    val name: String,
    var isChecked: Boolean = false
)