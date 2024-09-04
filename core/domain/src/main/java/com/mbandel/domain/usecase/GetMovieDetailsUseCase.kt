package com.mbandel.domain.usecase

import com.mbandel.domain.MovieDetailsStatus
import com.mbandel.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
): (Int) -> Flow<MovieDetailsStatus> {
    override fun invoke(movieId: Int): Flow<MovieDetailsStatus> {
        return movieDetailsRepository.getMovieDetails(movieId)
    }
}