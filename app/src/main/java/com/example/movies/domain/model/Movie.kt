package com.example.movies.domain.model

import android.graphics.Bitmap

data class Movie(
    val id: Int,
    val title: String?,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    var image: Bitmap?
)