package com.example.movies.domain.interactor

import com.example.movies.common.ImageSizes
import com.example.movies.common.Result
import com.example.movies.domain.repository.images.ImagesRepository
import java.io.InputStream

class GetImageUseCase(private val repository: ImagesRepository) {
    suspend operator fun invoke(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    ) = repository.getImage(path, size, onSuccess, onError)
}