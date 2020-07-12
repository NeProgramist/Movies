package com.example.movies.domain.repository.images

import com.example.movies.common.ImageSizes
import com.example.movies.common.Result
import com.example.movies.domain.repository.images.ImagesRepository
import com.example.movies.framework.datasource.remote.ImagesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class ImagesRepositoryImpl(
    private val remoteDataSource: ImagesRemoteDataSource
): ImagesRepository {
    override suspend fun getImage(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) { remoteDataSource.getImage(path, size, onSuccess, onError) }
}