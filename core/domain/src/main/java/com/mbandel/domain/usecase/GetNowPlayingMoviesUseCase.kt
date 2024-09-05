package com.mbandel.domain.usecase

import com.mbandel.domain.MovieListStatus
import com.mbandel.domain.MovieListViewDataStatus
import com.mbandel.domain.model.toViewData
import com.mbandel.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : () -> Flow<MovieListViewDataStatus> {
    override fun invoke(): Flow<MovieListViewDataStatus> {
        return moviesRepository.getNowPlayingMovies().map { status ->
            when (status) {
                MovieListStatus.Loading -> MovieListViewDataStatus.Loading
                is MovieListStatus.Success -> MovieListViewDataStatus.Success(status.movieInfo.map {
                    it.toViewData()
                })
                MovieListStatus.ServerError -> MovieListViewDataStatus.ServerError
                MovieListStatus.ConnectionError -> MovieListViewDataStatus.ConnectionError
            }
        }
    }
}