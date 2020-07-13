package com.example.movies.ui.detailed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Result
import com.example.movies.domain.interactor.GetMovieUseCase
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.repository.movies.MoviesRepositoryImpl
import com.example.movies.framework.datasource.remote.MoviesApi
import com.example.movies.framework.datasource.remote.MoviesRemoteDataSource
import com.example.movies.ui.BaseViewModel
import kotlinx.coroutines.launch

class DetailedViewModel: BaseViewModel() {
    val movie = MutableLiveData<Result<DetailedMovie>>()

    fun showMovie(id: Int) {
        movie.value = Result.loading()

        viewModelScope.launch {
            GetMovieUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), API_KEY)
                )
            )(
                id,
                onSuccess = { movie.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }
}