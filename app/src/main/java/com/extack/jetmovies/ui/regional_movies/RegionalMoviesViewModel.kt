package com.extack.jetmovies.ui.regional_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.extack.jetmovies.api.commons.onSuccess
import com.extack.jetmovies.domain.mapper.toGenre
import com.extack.jetmovies.domain.model.Genre
import com.extack.jetmovies.domain.repository.GenreRepository
import com.extack.jetmovies.domain.repository.RegionalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionalMoviesViewModel @Inject constructor(
    private val regionalMoviesRepository: RegionalMoviesRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {

    init {
        getMovieGenres()
    }

    private val _genreStateFlow = MutableStateFlow<List<Genre>>(emptyList())
    val genreStateFlow: StateFlow<List<Genre>> = _genreStateFlow

    fun getRegionalMovies(originalLang: String) =
        regionalMoviesRepository.getPagedRegionalMovies(originalLang).flow.cachedIn(viewModelScope)

    private fun getMovieGenres() {
        viewModelScope.launch {
            genreRepository.getMovieGenres()
                .onSuccess { genreResponse ->
                    _genreStateFlow.value = genreResponse.genres.map { it.toGenre() }
                }
        }
    }

    fun onGenreSelected(id: Long, isChecked: Boolean) {
        _genreStateFlow.apply {
            val genres = value
            genres.find { it.id == id }?.isChecked = isChecked
            val newList = mutableListOf<Genre>()
            newList.addAll(genres)
            _genreStateFlow.value = newList
        }
    }

}