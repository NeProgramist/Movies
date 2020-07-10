package com.example.movies.domain.interactor

import com.example.movies.domain.repository.MoviesRepository

class GetMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovie(id)
}