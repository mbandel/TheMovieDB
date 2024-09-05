package com.mbandel.nowplaying.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mbandel.details.ui.ErrorTextMessage
import com.mbandel.details.ui.MovieDetailsScreen
import com.mbandel.details.ui.ProgressIndicator
import com.mbandel.nowplaying.NowPlayingViewModel
import com.mbandel.nowplaying.R


object NowPlayingScreen : Screen {
    @Composable
    override fun Content() {
        NowPlayingMoviesComposableScreen(viewModel = getViewModel())
    }
}

@Composable
private fun NowPlayingMoviesComposableScreen(
    viewModel: NowPlayingViewModel
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val movieList = state.movieInfoList
    val navigator = LocalNavigator.currentOrThrow

    if (state.isLoading) {
        ProgressIndicator()
    }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        if (state.isConnectionError) {
            ErrorTextMessage(stringResource(R.string.connection_error))
        }

        if (state.isServerError) {
            ErrorTextMessage(stringResource(R.string.server_error))
        }

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(42.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(movieList) { movie ->
                MovieItem(movieName = movie.name,
                    isFavorite = movie.isFavorite,
                    onItemClicked = { navigator.push(MovieDetailsScreen(movieId = movie.id)) },
                    addToFavorite = { viewModel.addFavouriteMovieId(id = movie.id) },
                    removeFromFavorite = { viewModel.removeFavoriteMovieId(id = movie.id) }
                )
            }
        }
    }
}

@Composable
private fun MovieItem(
    movieName: String, isFavorite: Boolean,
    onItemClicked: () -> Unit,
    addToFavorite: () -> Unit,
    removeFromFavorite: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            fontWeight = FontWeight.Bold, text = movieName,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .clickable { onItemClicked() }
        )
        if (isFavorite) {
            Image(
                painter = painterResource(id = R.drawable.favorite_star),
                contentDescription = null,
                modifier = Modifier.clickable { removeFromFavorite() }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.empty_star),
                contentDescription = null,
                modifier = Modifier.clickable { addToFavorite() }
            )
        }
    }
}