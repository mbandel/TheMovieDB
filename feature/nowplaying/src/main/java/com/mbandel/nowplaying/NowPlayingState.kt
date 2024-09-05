package com.mbandel.nowplaying

import com.mbandel.domain.model.MovieInfoViewData

internal data class NowPlayingState(
    val movieInfoList: List<MovieInfoViewData> = emptyList(),
    val searchQuery: String = "",
    val isConnectionError: Boolean = false,
    val isServerError: Boolean = false,
    val isLoading: Boolean = false
)
