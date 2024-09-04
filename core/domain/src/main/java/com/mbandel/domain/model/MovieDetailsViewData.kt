package com.mbandel.domain.model

data class MovieDetailsViewData(
    val id: Int = -1,
    val backdropPath: String = "",
    val title: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val voteAverage: String = "",
    val isFavorite: Boolean = false
)