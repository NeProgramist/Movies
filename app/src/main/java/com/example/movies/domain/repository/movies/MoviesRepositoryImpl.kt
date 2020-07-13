package com.example.movies.domain.repository.movies

import com.example.movies.common.Result
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.model.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesDataSource
): MoviesRepository {
    override suspend fun getMovies(
        page: Int,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) { remoteDataSource.getMovies(onSuccess, onError) }

    override suspend fun getMovie(
        id: Int,
        onSuccess: (Result<DetailedMovie>) -> Unit,
        onError: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) { remoteDataSource.getMovie(id, onSuccess, onError) }

    override suspend fun search(
        page: Int,
        text: String,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) { remoteDataSource.searchMovie(text, onSuccess, onError) }
}
