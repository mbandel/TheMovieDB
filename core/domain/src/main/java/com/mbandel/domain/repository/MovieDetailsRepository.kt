package com.mbandel.domain.repository

import com.mbandel.api.ApiService
import com.mbandel.api.data.MovieDetailsRemote
import com.mbandel.domain.MovieDetailsStatus
import com.mbandel.domain.model.MovieDetails
import com.mbandel.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailsRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getMovieDetails(movieId: Int): Flow<MovieDetailsStatus> = flow {
        emit(MovieDetailsStatus.Loading)
        try {
            val request = apiService.getMovieDetails(movieId)
            if (request.isSuccessful) {
                val movieDetailsRemote = request.body()
                if (movieDetailsRemote != null) {
                    emit(MovieDetailsStatus.Success(movieDetails = movieDetailsRemote.toDomain()))
                }
            } else {
                emit(MovieDetailsStatus.ServerError)
            }
        } catch (e: Exception) {
            emit(MovieDetailsStatus.ConnectionError)
        }
    }
}