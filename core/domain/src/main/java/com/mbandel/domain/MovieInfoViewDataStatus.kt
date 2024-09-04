package com.mbandel.domain

import com.mbandel.domain.model.MovieInfoViewData

sealed interface MovieInfoViewDataStatus {
    data object Loading : MovieInfoViewDataStatus
    data class Success(val movieInfo: List<MovieInfoViewData>) : MovieInfoViewDataStatus
    data object ConnectionError : MovieInfoViewDataStatus
    data object ServerError : MovieInfoViewDataStatus
}