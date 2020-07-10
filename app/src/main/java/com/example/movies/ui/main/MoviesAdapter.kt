/*
package com.example.movies.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.model.Movie

class MoviesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(item)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(movies[position])
        }
    }

    fun setupMovies(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    class MovieViewHolder(item: View): RecyclerView.ViewHolder(item) {
        fun bind(item: Movie) {
        }
    }
}*/
