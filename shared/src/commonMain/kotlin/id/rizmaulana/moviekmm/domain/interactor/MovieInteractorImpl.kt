package id.rizmaulana.moviekmm.domain.interactor

import id.rizmaulana.moviekmm.data.MovieRepository
import id.rizmaulana.moviekmm.domain.model.Movie

class MovieInteractorImpl(private val movieRepository: MovieRepository) : MovieInteractor {
    override suspend fun getMovies(): List<Movie> {
        return movieRepository.getMovies()
    }

}