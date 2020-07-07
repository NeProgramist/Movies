package com.example.movies.data.interactor

import com.example.movies.data.repository.MoviesRepository

class GetMovieUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int) = repository.getMovie(id)
}