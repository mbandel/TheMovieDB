package com.mbandel.domain.model

data class MovieInfo(
    val id: Int = 0,
    val name: String = "",
)

fun MovieInfo.toViewData(): MovieInfoViewData {
    return MovieInfoViewData(id = this.id, name = this.name)
}