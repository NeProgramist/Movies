package com.example.movies.data

import com.example.movies.domain.model.Movie

interface MoviesDataSource {
    fun getMovies(): List<Movie>
    fun getMovie(id: Int): Movie
    fun searchMovie(text: String): List<Movie>
}