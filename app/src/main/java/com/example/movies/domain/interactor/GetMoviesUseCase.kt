package com.example.movies.domain.interactor

import com.example.movies.domain.model.MoviesList
import com.example.movies.common.Result
import com.example.movies.domain.repository.movies.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(
        page: Int,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = repository.getMovies(page, onSuccess, onError)
}