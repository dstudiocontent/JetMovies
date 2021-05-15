package com.extack.jetmovies.ui.commons.model

import java.io.Serializable

data class ScrollDetail(
    val position : Int,
    val offset : Int
) : Serializable