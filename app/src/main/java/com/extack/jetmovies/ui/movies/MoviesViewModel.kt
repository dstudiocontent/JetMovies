package com.extack.jetmovies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extack.jetmovies.api.commons.onFailure
import com.extack.jetmovies.api.commons.onSuccess
import com.extack.jetmovies.domain.mapper.toMovieList
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Language
import com.extack.jetmovies.domain.model.RegionalMovie
import com.extack.jetmovies.domain.repository.RegionalMoviesRepository
import com.extack.jetmovies.extensions.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val regionalMoviesRepository: RegionalMoviesRepository
) : ViewModel() {

    private val initialLanguages = listOf(
        Language("ml", "Malayalam"),
        Language("hi", "Hindi"),
        Language("ta", "Tamil"),
        Language("te", "Telugu"),
        Language("en", "English"),
    )

    init {
        fetchRegionalMovies()
    }

    private val _regionalMoviesStateFlow =
        MutableStateFlow<List<RegionalMovie>>(emptyList())
    val regionalMoviesStateFlow: StateFlow<List<RegionalMovie>> = _regionalMoviesStateFlow

    private fun fetchRegionalMovies() {
        initialLanguages.forEach { language ->
            viewModelScope.launch {
                regionalMoviesRepository.getRegionalMovies(originalLang = language.isoValue)
                    .onSuccess { movies ->
                        val regionalMovies =
                            RegionalMovie(language, movies.results.toMovieList(ImageType.POSTER))
                        _regionalMoviesStateFlow.apply {
                            value = value.toMutableList().plus(regionalMovies)
                        }
                    }
                    .onFailure { logError(it.errorMessage) }
            }
        }
    }
}