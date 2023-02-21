package id.rizmaulana.moviekmm.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("overview")
    val overview: String? = "",
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("vote_average")
    val voteAverage: Float = 0f
)