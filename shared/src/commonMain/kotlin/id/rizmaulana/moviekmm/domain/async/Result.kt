package id.rizmaulana.moviekmm.domain.async

sealed interface AsyncResult<out T> {
    data class Success<T>(val data: T) : AsyncResult<T>
    data class Error(val exceptionMessage: String) : AsyncResult<Nothing>
    object Loading : AsyncResult<Nothing>
    object Uninitialized : AsyncResult<Nothing>
}