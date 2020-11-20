package com.example.madlevel6task2.Api

import com.example.madlevel6task2.Model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("year") year: String? = ""): MovieResponse.Root
}