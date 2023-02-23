package id.rizmaulana.moviekmm.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import id.rizmaulana.moviekmm.domain.async.AsyncResult
import id.rizmaulana.moviekmm.domain.interactor.MovieInteractor
import id.rizmaulana.moviekmm.domain.model.Movie
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel constructor(private val interactor: MovieInteractor) : ViewModel() {

    private val moviesMutable =
        MutableStateFlow<AsyncResult>(AsyncResult.Uninitialized)
    val movies = moviesMutable.asStateFlow()

    private val selectedMovieMutable = MutableStateFlow<Movie?>(null)
    val selectedMovie = selectedMovieMutable.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        moviesMutable.value = AsyncResult.Loading
        viewModelScope.launch {
            try {
                val movie = interactor.getMovies()
                moviesMutable.value = AsyncResult.Success(movie)
            } catch (e: Exception) {
                e.printStackTrace()
                moviesMutable.value = AsyncResult.Error(e.message.orEmpty())
            }
        }
    }

    fun selectMovie(movie: Movie) {
        selectedMovieMutable.value = movie
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}