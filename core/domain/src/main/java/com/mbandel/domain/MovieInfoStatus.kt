package com.mbandel.domain

import com.mbandel.domain.model.MovieInfo


sealed interface MovieInfoStatus {
    data object Loading : MovieInfoStatus
    data class Success(val movieInfo: List<MovieInfo>) : MovieInfoStatus
    data object ConnectionError : MovieInfoStatus
    data object ServerError : MovieInfoStatus
}