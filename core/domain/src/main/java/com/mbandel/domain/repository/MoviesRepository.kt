package com.mbandel.domain.repository


import com.mbandel.api.ApiService
import com.mbandel.domain.MovieListStatus
import com.mbandel.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getNowPlayingMovies(): Flow<MovieListStatus> = flow {
        emit(MovieListStatus.Loading)
        try {
            val request = apiService.getNowPlaying()
            if (request.isSuccessful) {
                val recentMovies = request.body()
                if (recentMovies != null)
                    emit(
                        MovieListStatus.Success(
                            recentMovies.results.map { result ->
                                MovieInfo(
                                    id = result.id,
                                    name = result.title
                                )
                            })
                    )
            } else emit(MovieListStatus.ServerError)
        } catch (e: Exception) {
            emit(MovieListStatus.ConnectionError)
        }
    }

    fun getSearchedMovies(query: String): Flow<MovieListStatus> = flow {
        emit(MovieListStatus.Loading)
        try {
            val request = apiService.searchMovie(query)
            if (request.isSuccessful) {
                val searchedMovies = request.body()
                if (searchedMovies != null)
                    emit(
                        MovieListStatus.Success(
                            searchedMovies.results.map { result ->
                                MovieInfo(
                                    id = result.id,
                                    name = result.title
                                )
                            })
                    )
            } else emit(MovieListStatus.ServerError)
        } catch (e: Exception) {
            emit(MovieListStatus.ConnectionError)
        }
    }
}