package com.mbandel.details

import com.mbandel.domain.model.MovieDetailsViewData

internal data class MovieDetailsState(
    val movieDetailsViewData: MovieDetailsViewData = MovieDetailsViewData(),
    val isConnectionError: Boolean = false,
    val isServerError: Boolean = false,
    val isLoading: Boolean = false
)
