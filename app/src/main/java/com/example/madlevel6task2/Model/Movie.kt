package com.example.madlevel6task2.Model

import com.google.gson.annotations.SerializedName

class MovieResponse() {

    data class Root (
            @SerializedName("page") val page : Int,
            @SerializedName("total_results") val total_results : Int,
            @SerializedName("total_pages") val total_pages : Int,
            @SerializedName("results") val results : List<Movie>
    )

    data class Movie (
            @SerializedName("vote_count") val vote_count : Int,
            @SerializedName("poster_path") val poster_path : String,
            @SerializedName("id") val id : Int,
            @SerializedName("backdrop_path") val backdrop_path : String,
            @SerializedName("title") val title : String,
            @SerializedName("vote_average") val vote_average : Double,
            @SerializedName("overview") val overview : String,
            @SerializedName("release_date") val release_date : String
    ) {
        fun getMovieUrl(): String {
            return "https://image.tmdb.org/t/p/original%s".format(poster_path)
        }
    }

}