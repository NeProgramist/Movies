package com.example.movies.domain.interactor

import com.example.movies.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getMovies()
}