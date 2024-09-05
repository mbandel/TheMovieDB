package com.mbandel.domain

import com.mbandel.domain.model.MovieInfo


sealed interface MovieListStatus {
    data object Loading : MovieListStatus
    data class Success(val movieInfo: List<MovieInfo>) : MovieListStatus
    data object ConnectionError : MovieListStatus
    data object ServerError : MovieListStatus
}