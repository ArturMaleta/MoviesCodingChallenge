package mal.art.moviescodingchallenge.movie_db

import mal.art.moviescodingchallenge.model.PlayedMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbEndpoints {

    @GET("/3/movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Call<PlayedMovies>
}