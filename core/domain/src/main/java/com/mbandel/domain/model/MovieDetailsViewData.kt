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

fun MovieDetails.toViewData(): MovieDetailsViewData {
    return MovieDetailsViewData(
        id = this.id,
        backdropPath = this.backdropPath,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        voteAverage = String.format("%.2f", this.voteAverage)
    )
}