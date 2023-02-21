package id.rizmaulana.moviekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform