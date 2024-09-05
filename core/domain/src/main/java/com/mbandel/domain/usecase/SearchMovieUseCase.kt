package com.mbandel.domain.usecase

import com.mbandel.domain.MovieListStatus
import com.mbandel.domain.MovieListViewDataStatus
import com.mbandel.domain.model.toViewData
import com.mbandel.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : (String) -> Flow<MovieListViewDataStatus> {
    override fun invoke(query: String): Flow<MovieListViewDataStatus> {
        return moviesRepository.getSearchedMovies(query).map { status ->
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