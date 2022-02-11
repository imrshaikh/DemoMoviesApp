package com.example.moviesapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()
    private val movieListTypeToken =
        TypeToken.getParameterized(List::class.java, Movie::class.java)

    @TypeConverter
    fun movieListFromJson(json: String?): List<Movie>? {
        return gson.fromJson(json, movieListTypeToken.type)
    }

    @TypeConverter
    fun movieListToJson(movieList: List<Movie>?): String? {
        return gson.toJson(movieList)
    }

    @TypeConverter
    fun intListToJson(intList: List<Int>?): String? {
        return gson.toJson(intList)
    }

    @TypeConverter
    fun intListFromJson(json: String?): List<Int>? {
        return gson.fromJson(json, ArrayList<Int>().javaClass)
    }

}