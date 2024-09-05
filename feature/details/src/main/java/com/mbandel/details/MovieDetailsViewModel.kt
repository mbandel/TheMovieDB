package com.mbandel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbandel.domain.MovieDetailsViewDataStatus
import com.mbandel.domain.usecase.AddFavoriteMovieIdUseCase
import com.mbandel.domain.usecase.GetFavoriteMovieIdsUseCase
import com.mbandel.domain.usecase.GetMovieDetailsUseCase
import com.mbandel.domain.usecase.RemoveFavoriteMovieIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteMovieIdUseCase: AddFavoriteMovieIdUseCase,
    private val removeFavoriteMovieIdUseCase: RemoveFavoriteMovieIdUseCase,
    private val getFavoriteMovieIdsUseCase: GetFavoriteMovieIdsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MovieDetailsState> = MutableStateFlow(MovieDetailsState())
    internal val state: StateFlow<MovieDetailsState> = _state

    internal fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { status ->
                when (status) {
                    is MovieDetailsViewDataStatus.Success -> {
                        _state.update {
                            it.copy(
                                movieDetailsViewData = status.movieDetailsViewData,
                                isServerError = false,
                                isLoading = false,
                                isConnectionError = false
                            )
                        }
                        checkIfFavorite()
                    }

                    MovieDetailsViewDataStatus.ConnectionError -> {
                        _state.update { it.copy(isConnectionError = true, isLoading = false) }
                    }

                    MovieDetailsViewDataStatus.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    MovieDetailsViewDataStatus.ServerError -> {
                        _state.update { it.copy(isServerError = true, isLoading = false) }
                    }
                }
            }
        }
    }

    internal fun addFavouriteMovieId(id: Int) {
        viewModelScope.launch { addFavoriteMovieIdUseCase(id) }
    }

    internal fun removeFavoriteMovieId(id: Int) {
        viewModelScope.launch { removeFavoriteMovieIdUseCase(id) }
    }

    private suspend fun checkIfFavorite() {
        getFavoriteMovieIdsUseCase().collect { movieIds ->
            if (_state.value.movieDetailsViewData.id in movieIds) {
                _state.update {
                    it.copy(
                        movieDetailsViewData = _state.value.movieDetailsViewData.copy(
                            isFavorite = true
                        )
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        movieDetailsViewData = _state.value.movieDetailsViewData.copy(
                            isFavorite = false
                        )
                    )
                }

            }
        }
    }
}