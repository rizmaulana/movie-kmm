package id.rizmaulana.moviekmm.domain.model

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val posterUrl: String = "",
    val bannerUrl: String = "",
    val rating: Float = 0f,
    val releaseDate: String = ""
)