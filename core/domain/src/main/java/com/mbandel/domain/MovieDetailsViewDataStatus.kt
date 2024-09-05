package com.mbandel.domain

import com.mbandel.domain.model.MovieDetailsViewData

sealed interface MovieDetailsViewDataStatus {
    data object Loading : MovieDetailsViewDataStatus
    data class Success(val movieDetailsViewData: MovieDetailsViewData): MovieDetailsViewDataStatus
    data object ConnectionError: MovieDetailsViewDataStatus
    data object ServerError: MovieDetailsViewDataStatus
}