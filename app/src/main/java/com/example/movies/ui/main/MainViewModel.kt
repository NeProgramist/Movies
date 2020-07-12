package com.example.movies.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Result
import com.example.movies.domain.interactor.GetMoviesUseCase
import com.example.movies.domain.interactor.SearchMoviesUseCase
import com.example.movies.domain.model.MoviesList
import com.example.movies.domain.repository.movies.MoviesRepositoryImpl
import com.example.movies.framework.datasource.remote.MoviesApi
import com.example.movies.framework.datasource.remote.MoviesRemoteDataSource
import com.example.movies.ui.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {
    val movies = MutableLiveData<Result<MoviesList>>()
    val searchedMovie = MutableLiveData<Result<MoviesList>>()

    fun showMovieList() {
        movies.value = Result.loading()

        viewModelScope.launch {
            GetMoviesUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), "b0d4f703c67f14464200a9c01a4ba190")
                )
            )(
                onSuccess = { movies.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }

    fun searchMovies(text: String) {
        searchedMovie.value = Result.loading()

        viewModelScope.launch {
            SearchMoviesUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), "b0d4f703c67f14464200a9c01a4ba190")
                )
            )(
                text,
                onSuccess = { searchedMovie.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }
}
