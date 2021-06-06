package com.extack.jetmovies.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.extack.jetmovies.api.commons.Resource
import com.extack.jetmovies.api.commons.apiCaller
import com.extack.jetmovies.api.endpoints.MoviesApi
import com.extack.jetmovies.api.response.MovieListResponse
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Movie
import javax.inject.Inject

class RegionalMoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi
) {
    suspend fun getRegionalMovies(
        originalLang: String
    ): Resource<MovieListResponse> {
        return apiCaller {
            moviesApi.getRegionalMovies(originalLang)
        }
    }

    fun getPagedRegionalMovies(originalLang: String) = Pager(PagingConfig(pageSize = 20)) {
        RegionalMovieSource(moviesApi, originalLang)
    }
}

class RegionalMovieSource(
    private val moviesApi: MoviesApi,
    private val originalLang: String,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            val moviesListResponse = moviesApi.getRegionalMovies(originalLang, page)

            val nextKey =
                if (moviesListResponse.results.isEmpty()) null
                else moviesListResponse.page.plus(1)

            LoadResult.Page(
                data = moviesListResponse.results.map { it.toMovie(ImageType.POSTER) },
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}