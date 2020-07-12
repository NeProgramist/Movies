package com.example.movies.framework.datasource.remote

import android.util.Log
import com.example.movies.common.ImageSizes
import com.example.movies.common.Result
import com.example.movies.data.ImagesDataSource
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class ImagesRemoteDataSource: ImagesDataSource {
    override suspend fun getImage(
        path: String,
        size: ImageSizes,
        onSuccess: (Result<InputStream>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://image.tmdb.org/t/p/${size.size}$path").build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
//                Log.d("adsfas", response.body())

                if (response.isSuccessful) onSuccess(Result.success(response.body()?.byteStream()))
                else onSuccess(Result.error(Error("Troubles with getImages response")))
            }
        })
    }
}
