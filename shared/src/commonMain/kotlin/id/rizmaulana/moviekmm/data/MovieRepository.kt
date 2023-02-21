package id.rizmaulana.moviekmm.data

import id.rizmaulana.moviekmm.data.mapper.MovieDataMapper
import id.rizmaulana.moviekmm.data.source.MovieRemoteDataSource
import id.rizmaulana.moviekmm.domain.model.Movie

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val dataMapper: MovieDataMapper
) {

    suspend fun getMovies() : List<Movie> = remoteDataSource.getMovies().data.map {
        dataMapper.mapMovieResponse(it)
    }

}