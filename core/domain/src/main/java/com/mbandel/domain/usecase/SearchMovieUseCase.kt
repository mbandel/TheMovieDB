package com.mbandel.domain.usecase

import com.mbandel.domain.MovieInfoStatus
import com.mbandel.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : (String) -> Flow<MovieInfoStatus> {
    override fun invoke(query: String): Flow<MovieInfoStatus> {
        return moviesRepository.getSearchedMovies(query)
    }
}