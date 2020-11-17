package com.example.madlevel6task2.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.Api.MovieApi
import com.example.madlevel6task2.Api.MovieApiService
import com.example.madlevel6task2.Model.MovieResponse
import kotlinx.coroutines.withTimeout


class MovieError(message: String, cause: Throwable) : Throwable(message, cause)

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createApi()
    private val _movies: MutableLiveData<List<MovieResponse.Movie>> = MutableLiveData()


    val movies: LiveData<List<MovieResponse.Movie>> get() = _movies

    suspend fun getMovies(year: String?) {
        try {
            val parsedMovieResponse  = withTimeout(5_000) {
                movieApiService.getMovies(year)
            }

            _movies.value = parsedMovieResponse.results

        } catch (error: Throwable) {
            throw MovieError("Unable to get movies", error)
        }
    }
}