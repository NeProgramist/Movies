package com.example.movies.domain.repository

import com.example.movies.domain.model.Movie

interface MoviesRepository {
    fun getMovies(): List<Movie>

    fun getMovie(id: Int): Movie
}