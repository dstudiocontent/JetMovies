package com.extack.jetmovies.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.domain.repository.PopularMoviesRepository
import com.extack.jetmovies.domain.repository.RegionalMoviesRepository
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logError
import com.extack.jetmovies.extensions.logInfo
import com.extack.jetmovies.ui.commons.model.ScrollDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    popularMoviesRepository: PopularMoviesRepository
) : ViewModel() {
    val movies = popularMoviesRepository.getPopularMovies().flow.cachedIn(viewModelScope)
}