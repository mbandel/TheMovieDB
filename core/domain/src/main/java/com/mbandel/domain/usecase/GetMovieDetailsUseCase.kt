package com.mbandel.domain.usecase

import com.mbandel.domain.MovieDetailsStatus
import com.mbandel.domain.MovieDetailsViewDataStatus
import com.mbandel.domain.model.toViewData
import com.mbandel.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
): (Int) -> Flow<MovieDetailsViewDataStatus> {
    override fun invoke(movieId: Int): Flow<MovieDetailsViewDataStatus> {
        return movieDetailsRepository.getMovieDetails(movieId).map { status ->
            when(status) {
                MovieDetailsStatus.Loading -> MovieDetailsViewDataStatus.Loading
                is MovieDetailsStatus.Success -> MovieDetailsViewDataStatus.Success(status.movieDetails.toViewData())
                MovieDetailsStatus.ServerError -> MovieDetailsViewDataStatus.ServerError
                MovieDetailsStatus.ConnectionError -> MovieDetailsViewDataStatus.ConnectionError
            }
        }
    }
}