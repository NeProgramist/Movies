package com.example.movies.domain.repository.images

import com.example.movies.common.ImageSizes
import com.example.movies.common.Result
import java.io.InputStream

interface ImagesRepository {
    suspend fun getImage(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
