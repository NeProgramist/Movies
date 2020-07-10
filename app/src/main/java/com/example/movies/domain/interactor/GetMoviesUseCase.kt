package com.example.movies.domain.interactor

import com.example.movies.domain.model.MoviesList
import com.example.movies.common.Result
import com.example.movies.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit) =
        repository.getMovies(onSuccess, onError)
}