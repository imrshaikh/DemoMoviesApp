package com.example.moviesapp.retrofit

import com.example.moviesapp.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {


    private var retroInstance: Retrofit? = null
    private var movieService: MovieService? = null

    fun create(): Retrofit {
        if (retroInstance == null) {

            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            retroInstance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .build()
        }

        return retroInstance!!
    }

    fun getMovieService(): MovieService {
        if (movieService == null) {
            movieService = create().create(MovieService::class.java)
        }

        return movieService!!
    }

}