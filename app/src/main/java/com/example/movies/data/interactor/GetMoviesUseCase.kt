package com.example.movies.data.interactor

import com.example.movies.data.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getMovies()
}