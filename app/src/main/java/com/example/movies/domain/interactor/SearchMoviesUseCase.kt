package com.example.movies.domain.interactor

import com.example.movies.domain.repository.MoviesRepository

class SearchMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(text: String) = repository.search(text)
}