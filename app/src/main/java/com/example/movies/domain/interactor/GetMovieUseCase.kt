package com.example.movies.domain.interactor

import com.example.movies.common.Result
import com.example.movies.domain.model.Movie
import com.example.movies.domain.repository.MoviesRepository

class GetMovieUseCase(private val repository: MoviesRepository) {
    operator fun invoke(
        id: Int,
        onSuccess: (Result<Movie>) -> Unit,
        onError: (Throwable) -> Unit
    ) = repository.getMovie(id, onSuccess, onError)
}