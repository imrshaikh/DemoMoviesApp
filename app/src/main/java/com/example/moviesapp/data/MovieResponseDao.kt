package com.example.moviesapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class MovieResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(moviesPageResponse: MoviesPageResponse)

    @Query("SELECT * FROM page_responses ORDER BY page DESC LIMIT 1")
    abstract suspend fun getLastMovieResponse(): MoviesPageResponse?

    @Query("SELECT * FROM page_responses WHERE page = :p")
    abstract suspend fun getPage(p: Int): MoviesPageResponse?

    @Query("DELETE FROM page_responses")
    abstract suspend fun clearAll()

}