package com.extack.jetmovies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    val isoValue: String,
    val englishName: String
) : Parcelable