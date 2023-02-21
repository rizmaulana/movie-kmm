package id.rizmaulana.moviekmm.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class BaseResponse<T> (
    @SerialName("results")
    val data: T
)