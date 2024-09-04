package com.mbandel.domain.model

import com.mbandel.api.data.MovieDetailsRemote

data class MovieDetails(
    val id: Int = -1,
    val backdropPath: String = "",
    val title: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val isFavorite: Boolean = false
)

fun MovieDetailsRemote.toDomain(): MovieDetails {
    return MovieDetails(
        id = this.id,
        backdropPath = this.backdropPath,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}