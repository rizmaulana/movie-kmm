package id.rizmaulana.moviekmm.android.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import id.rizmaulana.moviekmm.viewmodel.MovieViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun MovieScreen(viewModel: MovieViewModel = getViewModel()) {
    Text("Hi")

}


