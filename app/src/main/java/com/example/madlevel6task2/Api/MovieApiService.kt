package com.example.madlevel6task2.Api

import android.graphics.Movie
import com.example.madlevel6task2.Model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("year") year: String? = ""): MovieResponse.Root
}