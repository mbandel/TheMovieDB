package com.mbandel.domain.usecase

import com.mbandel.domain.MovieInfoStatus
import com.mbandel.domain.MovieInfoViewDataStatus
import com.mbandel.domain.model.MovieInfoViewData
import com.mbandel.domain.model.toViewData
import com.mbandel.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : () -> Flow<MovieInfoViewDataStatus> {
    override fun invoke(): Flow<MovieInfoViewDataStatus> {
        return moviesRepository.getNowPlayingMovies().map { status ->
            when (status) {
                MovieInfoStatus.Loading -> MovieInfoViewDataStatus.Loading
                is MovieInfoStatus.Success -> MovieInfoViewDataStatus.Success(status.movieInfo.map {
                    it.toViewData()
                })
                MovieInfoStatus.ServerError -> MovieInfoViewDataStatus.ServerError
                MovieInfoStatus.ConnectionError -> MovieInfoViewDataStatus.ConnectionError
            }
        }
    }
}