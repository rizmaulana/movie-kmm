package id.rizmaulana.moviekmm.data.source

import id.rizmaulana.moviekmm.data.constant.NetworkConstant.API_KEY
import id.rizmaulana.moviekmm.data.constant.NetworkConstant.BASE_URL
import id.rizmaulana.moviekmm.data.model.BaseResponse
import id.rizmaulana.moviekmm.data.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getMovies(): BaseResponse<List<MovieResponse>> {
        return httpClient.get("${BASE_URL}movie/now_playing") {
            parameter("api_key", API_KEY)
        }.body()
    }
}


