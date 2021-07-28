package com.extack.jetmovies.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.extack.jetmovies.api.endpoints.MoviesApi
import com.extack.jetmovies.domain.mapper.toMovie
import com.extack.jetmovies.domain.model.ImageType
import com.extack.jetmovies.domain.model.Movie
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val movieSource: MovieSource
) {
    fun getPopularMovies() = Pager(PagingConfig(pageSize = 20)) { movieSource }
}

class MovieSource @Inject constructor(
    private val moviesApi: MoviesApi
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val moviesListResponse = moviesApi.getPopularMovies(nextPage)

            LoadResult.Page(
                data = moviesListResponse.results.map { it.toMovie(ImageType.BACKDROP) },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (moviesListResponse.page > 5) null else moviesListResponse.page.plus(1)
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