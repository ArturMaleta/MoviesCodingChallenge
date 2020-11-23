package mal.art.moviescodingchallenge.repository

import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mal.art.moviescodingchallenge.Base
import mal.art.moviescodingchallenge.adapter.MoviesAdapter
import mal.art.moviescodingchallenge.model.Movie
import mal.art.moviescodingchallenge.model.PlayedMovies
import mal.art.moviescodingchallenge.movie_db.MovieDbEndpoints
import mal.art.moviescodingchallenge.movie_db.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {
    private val request = ServiceBuilder.buildService(MovieDbEndpoints::class.java)
    private val call = request.getMovies(Base.apiKey)
    private var list: List<Movie> = arrayListOf()

    fun getCall(): List<Movie> {
        call.enqueue(object : Callback<PlayedMovies> {
            override fun onResponse(call: Call<PlayedMovies>, response: Response<PlayedMovies>) {
                if (response.isSuccessful) {
                    list = response.body()!!.results
                    }
                }

            override fun onFailure(call: Call<PlayedMovies>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return list
    }
}