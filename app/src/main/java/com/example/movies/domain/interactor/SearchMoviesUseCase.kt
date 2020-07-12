package com.example.movies.domain.interactor

import com.example.movies.common.Result
import com.example.movies.domain.model.MoviesList
import com.example.movies.domain.repository.movies.MoviesRepository

class SearchMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(
        text: String,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = repository.search(text, onSuccess, onError)
}