package com.example.madlevel6task2.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.Model.MovieResponse
import com.example.madlevel6task2.R
import com.example.madlevel6task2.Viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_overview.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieOverviewFragment : Fragment() {

    private val movies = arrayListOf<MovieResponse.Movie>()
    private val moviesAdapter = MovieAdapter(movies)
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()

        btn_submit.setOnClickListener {
            val year = inputYear.text

            Log.d("SEARCH", "Getting movies for year: %s".format(year))
            movieViewModel.getMovies(year.toString())
        }

        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        })

    }

    private fun initRv() {
        rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
    }
}