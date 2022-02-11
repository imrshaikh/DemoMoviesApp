package com.example.moviesapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.Constants
import com.example.moviesapp.data.AppDatabase
import com.example.moviesapp.data.Movie
import com.example.moviesapp.retrofit.MovieService

class MoviePagingSource(
    val appDatabase: AppDatabase,
    val movieService: MovieService
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            var response = appDatabase.moviePageDao().getPage(nextPageNumber)
            if (response == null) {
                response = movieService.getTopMovies(Constants.API_KEY, nextPageNumber)
                appDatabase.moviePageDao().insert(response)
            }

            LoadResult.Page(
                data = response.results!!,
                prevKey = if (response.page == 1) null else response.page - 1,
                nextKey = if (response.page == response.totalPages) null else response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}