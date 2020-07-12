package com.example.movies.domain.model

import java.io.InputStream

data class DetailedMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val genres: List<Genres>,
    val release_date: String,
    var image: InputStream?,
    val credits: Credits
)