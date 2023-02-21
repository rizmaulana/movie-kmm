package id.rizmaulana.moviekmm.domain.interactor

import id.rizmaulana.moviekmm.domain.model.Movie

interface MovieInteractor {

    suspend fun getMovies(): List<Movie>

}