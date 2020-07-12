package com.example.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.ImageSizes
import com.example.movies.common.Result
import com.example.movies.domain.interactor.GetImageUseCase
import com.example.movies.domain.repository.images.ImagesRepositoryImpl
import com.example.movies.framework.datasource.remote.ImagesRemoteDataSource
import kotlinx.coroutines.launch
import java.io.InputStream

open class BaseViewModel: ViewModel() {
    fun getImage(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            GetImageUseCase(
                ImagesRepositoryImpl(
                    ImagesRemoteDataSource()
                )
            )(
                path,
                size,
                onSuccess,
                onError
            )
        }
    }
}