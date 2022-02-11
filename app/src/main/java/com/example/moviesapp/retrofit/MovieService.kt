package com.example.moviesapp.retrofit

import retrofit2.http.GET
import com.example.moviesapp.data.MoviesPageResponse
import retrofit2.Call
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated")
    suspend fun getTopMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int
    ): MoviesPageResponse
}