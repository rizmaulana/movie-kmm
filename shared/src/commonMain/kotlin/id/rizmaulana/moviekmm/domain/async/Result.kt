package id.rizmaulana.moviekmm.domain.async

import id.rizmaulana.moviekmm.domain.model.Movie

sealed interface AsyncResult {
    data class Success(val data: List<Movie>) : AsyncResult
    data class Error(val exceptionMessage: String) : AsyncResult
    object Loading : AsyncResult
    object Uninitialized : AsyncResult
}