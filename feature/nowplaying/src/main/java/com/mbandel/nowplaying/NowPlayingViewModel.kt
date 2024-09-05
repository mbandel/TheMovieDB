package com.mbandel.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbandel.domain.MovieListViewDataStatus
import com.mbandel.domain.usecase.AddFavoriteMovieIdUseCase
import com.mbandel.domain.usecase.GetFavoriteMovieIdsUseCase
import com.mbandel.domain.usecase.GetNowPlayingMoviesUseCase
import com.mbandel.domain.usecase.RemoveFavoriteMovieIdUseCase
import com.mbandel.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val addFavoriteMovieIdUseCase: AddFavoriteMovieIdUseCase,
    private val removeFavoriteMovieIdUseCase: RemoveFavoriteMovieIdUseCase,
    private val getFavoriteMovieIdsUseCase: GetFavoriteMovieIdsUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NowPlayingState> = MutableStateFlow(NowPlayingState())
    val state: StateFlow<NowPlayingState> = _state

    init {
        getNowPlayingMovies()
    }

    fun addFavouriteMovieId(id: Int) {
        viewModelScope.launch { addFavoriteMovieIdUseCase(id) }
    }

    fun removeFavoriteMovieId(id: Int) {
        viewModelScope.launch { removeFavoriteMovieIdUseCase(id) }
    }

    fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchForMovies()
    }

    private fun searchForMovies() {
        viewModelScope.launch {
            if (state.value.searchQuery != "") {
                searchMovieUseCase(state.value.searchQuery).collect { status ->
                    when (status) {
                        is MovieListViewDataStatus.Success -> {
                            _state.update {
                                it.copy(
                                    movieInfoList = status.movieInfo,
                                    isConnectionError = false,
                                    isServerError = false,
                                    isLoading = false
                                )
                            }
                            checkIfFavorite()
                        }

                        MovieListViewDataStatus.ConnectionError -> {
                            _state.update { it.copy(isConnectionError = true) }
                        }
                        MovieListViewDataStatus.ServerError -> {
                            _state.update { it.copy(isServerError = true) }
                        }
                        MovieListViewDataStatus.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }
                    }
                }
            } else {
                getNowPlayingMovies()
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collect { status ->
                when (status) {
                    is MovieListViewDataStatus.Success -> {
                        _state.update { it.copy(movieInfoList = status.movieInfo) }
                        checkIfFavorite()
                    }

                    MovieListViewDataStatus.ConnectionError -> {
                        _state.update { it.copy(isConnectionError = true) }
                    }

                    MovieListViewDataStatus.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    MovieListViewDataStatus.ServerError -> {
                        _state.update { it.copy(isServerError = true) }
                    }
                }
            }
        }
    }

    private suspend fun checkIfFavorite() {
        getFavoriteMovieIdsUseCase().collect { movieIds ->
            for (movie in state.value.movieInfoList) {
                if (movie.id in movieIds) {
                    _state.update {
                        it.copy(movieInfoList = state.value.movieInfoList.map { viewData ->
                            if (viewData.id == movie.id)
                                viewData.copy(isFavorite = true)
                            else viewData
                        })
                    }
                } else {
                    _state.update {
                        it.copy(movieInfoList = state.value.movieInfoList.map { viewData ->
                            if (viewData.id == movie.id)
                                viewData.copy(isFavorite = false)
                            else viewData
                        })
                    }
                }
            }
        }
    }
}