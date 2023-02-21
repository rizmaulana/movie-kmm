package id.rizmaulana.moviekmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    @SerialName("results")
    val data: T
)