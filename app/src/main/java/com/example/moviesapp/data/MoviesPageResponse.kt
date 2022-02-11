package com.example.moviesapp.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "page_responses")
data class MoviesPageResponse (

    @PrimaryKey
    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int?,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int?
) {

    constructor() : this(0, null, null, null)

}