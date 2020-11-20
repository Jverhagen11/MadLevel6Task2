package com.example.madlevel6task2.UI

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.Model.MovieResponse
import com.example.madlevel6task2.R
import com.example.madlevel6task2.Viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_overview.*


const val BUNDLE_MOVIE_KEY = "bundle_movie_key"
const val REQ_MOVIE_KEY = "req_movie_key"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieOverviewFragment : Fragment() {

    private val movies = arrayListOf<MovieResponse.Movie>()
    private val moviesAdapter = MovieAdapter(movies, ::onMovieClick)
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
        activity?.setTitle("Popular movies")
        initRv()

        btn_submit.setOnClickListener {
            val year = inputYear.text

            if(year?.isEmpty()!!) {
                Snackbar.make(btn_submit, R.string.failed, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            movieViewModel.getMovies(year.toString())
        }

        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        })

        movieViewModel.errorText.observe(viewLifecycleOwner, Observer {
            Snackbar.make(btn_submit, R.string.failed, Snackbar.LENGTH_SHORT).show()
        })

    }

    private fun onMovieClick(movie: MovieResponse.Movie) {
        setFragmentResult(REQ_MOVIE_KEY, bundleOf(Pair(BUNDLE_MOVIE_KEY, movie)))
        findNavController().navigate(R.id.action_movieOverviewFragment_to_movieInfoFragment)
    }

    private fun initRv() {
        rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
    }
}