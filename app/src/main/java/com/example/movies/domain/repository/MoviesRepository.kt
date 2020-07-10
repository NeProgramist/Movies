package com.example.movies.domain.repository

import com.example.movies.domain.model.Movie
import com.example.movies.common.Result
import com.example.movies.domain.model.MoviesList

interface MoviesRepository {
    fun getMovies(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit)
    fun getMovie(id: Int): Result<Movie>
    fun search(text: String): Result<List<Movie>>
}