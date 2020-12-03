package mal.art.moviescodingchallenge.movie_db

import io.reactivex.Single
import mal.art.moviescodingchallenge.model.ListResponse
import mal.art.moviescodingchallenge.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbEndpoints {

    @GET("/3/movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Single<ListResponse<Movie>>

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Single<Movie>
}