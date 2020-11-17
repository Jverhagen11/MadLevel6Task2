package com.example.madlevel6task2.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.Model.MovieResponse
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.ItemMovieBinding

class MovieAdapter (private val movies: List<MovieResponse.Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = ItemMovieBinding.bind(itemView)

        fun dataBind(movie: MovieResponse.Movie) {
            binding.movieNumber.text = "%s.".format((movies.indexOf(movie)+1).toString())
            Glide.with(context).load(movie.getMovieUrl()).into(binding.movieImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(movies[position])
    }
}