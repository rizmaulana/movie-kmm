package id.rizmaulana.moviekmm.android.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.rizmaulana.moviekmm.android.component.MovieDetailComponent
import id.rizmaulana.moviekmm.android.component.MoviePlaySection
import id.rizmaulana.moviekmm.domain.async.AsyncResult
import id.rizmaulana.moviekmm.domain.model.Movie
import id.rizmaulana.moviekmm.viewmodel.MovieViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun MovieScreen(viewModel: MovieViewModel = getViewModel()) {
    val moviesAsync by viewModel.movies.collectAsState()
    val selectedMovie by viewModel.selectedMovie.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        val modifier = Modifier.padding(it)
        when (val async = moviesAsync) {
            is AsyncResult.Error -> ErrorScreen(modifier) {
                viewModel.loadMovies()
            }

            is AsyncResult.Success -> ContentScreen(
                modifier,
                movies = async.data,
                selectedMovie = selectedMovie
            ) { movie ->
                viewModel.selectMovie(movie)
            }

            else -> LoadingScreen(modifier)
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier, onTryAgain: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = ":(",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Black,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = "Cannot proceed your request, please try again!"
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(onClick = onTryAgain) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = "Loading ...."
        )
    }

}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier, movies: List<Movie>,
    selectedMovie: Movie?,
    onMovieSelected: (Movie) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        MoviePlaySection(
            title = "Now Playing",
            subtitle = "Watch your favorites movie of the year",
            movies = movies,
        ) {
            onMovieSelected.invoke(it)
        }
        Text(
            text = "Select movie poster to see detail",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
        )
        if (selectedMovie != null) {
            MovieDetailComponent(movie = selectedMovie)
        }
    }
}

