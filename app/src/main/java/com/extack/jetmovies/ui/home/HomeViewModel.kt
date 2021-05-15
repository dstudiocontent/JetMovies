package com.extack.jetmovies.ui.home

import androidx.lifecycle.*
import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.repository.PopularMoviesRepository
import com.extack.jetmovies.api.repository.RegionalMoviesRepository
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
    private val popularMoviesRepository: PopularMoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    init {
        fetchPopularMovies()
    }

    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData: LiveData<List<Movie>> = _popularMoviesLiveData

    fun saveScrollPositionWithKey(key: String, position: Int, offset: Int) {
        logInfo("Saving... $position")
        savedStateHandle[key] = ScrollDetail(position,offset)
    }

    fun getScrollPositionByKey(key: String): ScrollDetail {
        return savedStateHandle[key]?:ScrollDetail(0,0)
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            when (val apiCall = popularMoviesRepository.getPopularMovies()) {
                is Resource.Success -> {
                    _popularMoviesLiveData.value = apiCall.data.results.map {
                        it.toMovie()
                    }
                }
                is Resource.Failure -> logError(apiCall.error.errorMessage)
            }
        }
    }


}