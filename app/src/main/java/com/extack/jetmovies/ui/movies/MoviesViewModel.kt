package com.extack.jetmovies.ui.movies

import androidx.lifecycle.*
import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.repository.RegionalMoviesRepository
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logError
import com.extack.jetmovies.extensions.logInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val regionalMoviesRepository: RegionalMoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        fetchRegionalMovies()
    }

    private val _regionalMoviesLiveData = MutableLiveData<List<Movie>>()
    val regionalMoviesLiveData: LiveData<List<Movie>> = _regionalMoviesLiveData

    fun savePosition(firstItemIndex : Int){
        logInfo("Saving : $firstItemIndex")
        savedStateHandle["mal"] = firstItemIndex
    }

    fun getPosition() = savedStateHandle["mal"]?:0

    private fun fetchRegionalMovies() {
        viewModelScope.launch {
            when (val apiCall = regionalMoviesRepository.getRegionalMovies(
                region = "IN",
                originalLang = "ml"
            )) {
                is Resource.Success -> {
                    _regionalMoviesLiveData.value = apiCall.data.results.map {
                        it.toMovie()
                    }
                }
                is Resource.Failure -> logError(apiCall.error.errorMessage)
            }
        }
    }
}