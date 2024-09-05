package com.mbandel.domain

import com.mbandel.domain.model.MovieDetails

sealed interface MovieDetailsStatus {
    data object Loading : MovieDetailsStatus
    data class Success(val movieDetails: MovieDetails): MovieDetailsStatus
    data object ConnectionError: MovieDetailsStatus
    data object ServerError: MovieDetailsStatus
}