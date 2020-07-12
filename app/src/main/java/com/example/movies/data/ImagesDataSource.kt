package com.example.movies.data

import java.io.InputStream
import com.example.movies.common.ImageSizes
import com.example.movies.common.Result

interface ImagesDataSource {
    suspend fun getImage(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    )
}