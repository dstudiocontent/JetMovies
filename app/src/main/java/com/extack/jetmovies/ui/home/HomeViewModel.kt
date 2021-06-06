package com.extack.jetmovies.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.extack.jetmovies.domain.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    popularMoviesRepository: PopularMoviesRepository
) : ViewModel() {
    val movies = popularMoviesRepository.getPopularMovies().flow.cachedIn(viewModelScope)
}