package com.example.movies.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.model.Movie
import org.w3c.dom.Text

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
        private val posterId = item.findViewById<ImageView>(R.id.poster)
        private val nameId = item.findViewById<TextView>(R.id.movie_name)
        private val description = item.findViewById<TextView>(R.id.movie_description)

        fun bind(movie: Movie) {
            nameId.text = movie.title
            description.text = movie.overview
        }
    }
}
