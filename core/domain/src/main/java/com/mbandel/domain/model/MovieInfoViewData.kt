package com.mbandel.domain.model

data class MovieInfoViewData(
    val id: Int = 0,
    val name: String = "",
    val isFavorite: Boolean = false
)

fun MovieInfo.toViewData(): MovieInfoViewData {
    return MovieInfoViewData(id = this.id, name = this.name)
}