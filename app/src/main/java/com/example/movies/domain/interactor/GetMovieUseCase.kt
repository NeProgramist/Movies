package com.example.movies.domain.interactor

import com.example.movies.common.Result
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.repository.movies.MoviesRepository

class GetMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(
        id: Int,
        onSuccess: (Result<DetailedMovie>) -> Unit,
        onError: (Throwable) -> Unit
    ) = repository.getMovie(id, onSuccess, onError)
}