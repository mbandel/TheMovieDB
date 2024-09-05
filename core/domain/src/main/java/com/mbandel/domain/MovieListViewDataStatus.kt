package com.mbandel.domain

import com.mbandel.domain.model.MovieInfoViewData

sealed interface MovieListViewDataStatus {
    data object Loading : MovieListViewDataStatus
    data class Success(val movieInfo: List<MovieInfoViewData>) : MovieListViewDataStatus
    data object ConnectionError : MovieListViewDataStatus
    data object ServerError : MovieListViewDataStatus
}