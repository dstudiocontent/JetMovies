package com.extack.jetmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extack.jetmovies.api.PopularMovie
import com.extack.jetmovies.api.PopularMoviesApi
import com.extack.jetmovies.api.PopularMoviesRepository
import com.extack.jetmovies.api.other.Resource
import com.extack.jetmovies.extensions.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) : ViewModel() {
    private val _popularMoviesLiveData = MutableLiveData<List<PopularMovie>>()
    val popularMoviesLiveData: LiveData<List<PopularMovie>> = _popularMoviesLiveData
    fun getPopularMovies() {
        viewModelScope.launch {
            when (val apiCall = popularMoviesRepository.getPopularMovies()) {
                is Resource.Success -> _popularMoviesLiveData.value = apiCall.data.results
                is Resource.Failure -> logError(apiCall.error.message)
            }
        }
    }
}