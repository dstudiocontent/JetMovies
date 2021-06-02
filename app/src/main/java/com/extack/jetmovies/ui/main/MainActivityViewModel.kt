package com.extack.jetmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extack.jetmovies.domain.repository.PopularMoviesRepository
import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.domain.repository.RegionalMoviesRepository
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.Movie
import com.extack.jetmovies.extensions.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {


}