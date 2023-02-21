package id.rizmaulana.moviekmm.data.mapper

import id.rizmaulana.moviekmm.data.constant.NetworkConstant
import id.rizmaulana.moviekmm.data.model.MovieResponse
import id.rizmaulana.moviekmm.domain.model.Movie


class MovieDataMapper {

    fun mapMovieResponse(response: MovieResponse) = Movie(
        id = response.id,
        title = response.title.orEmpty(),
        description = response.overview.orEmpty(),
        posterUrl = buildString {
            append(NetworkConstant.POSTER_BASE_URL)
            append(response.posterPath.orEmpty())
        },
        bannerUrl = buildString {
            append(NetworkConstant.BACKDROP_BASE_URL)
            append(response.backdropPath.orEmpty())
        },
        rating = response.voteAverage,
        releaseDate = response.releaseDate.orEmpty()
    )



}