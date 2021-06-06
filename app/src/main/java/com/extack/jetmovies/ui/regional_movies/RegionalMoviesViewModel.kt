package com.extack.jetmovies.ui.regional_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.extack.jetmovies.domain.repository.RegionalMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegionalMoviesViewModel @Inject constructor(
    private val regionalMoviesRepository: RegionalMoviesRepository
) : ViewModel() {
    fun getRegionalMovies(originalLang : String) =
        regionalMoviesRepository.getPagedRegionalMovies(originalLang).flow.cachedIn(viewModelScope)


}