package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.moviesapp.data.AppDatabase
import com.example.moviesapp.data.Movie
import com.example.moviesapp.paging.MoviePagingSource
import com.example.moviesapp.retrofit.MovieClient

@ExperimentalPagingApi
class ItemListViewModel(application: Application): AndroidViewModel(application) {

    var livePagingData: LiveData<PagingData<Movie>>

    private val NETWORK_PAGE_SIZE = 20

    init {
        livePagingData = loadData()
    }

    private fun loadData(): LiveData<PagingData<Movie>> {
        val db = AppDatabase.getInstance(getApplication())
        val service = MovieClient.getMovieService()
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
                pagingSourceFactory = { MoviePagingSource(db, service) }
            ).liveData
    }

}