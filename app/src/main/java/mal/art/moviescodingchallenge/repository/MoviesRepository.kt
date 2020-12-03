package mal.art.moviescodingchallenge.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mal.art.moviescodingchallenge.Base
import mal.art.moviescodingchallenge.movie_db.MovieDbEndpoints
import mal.art.moviescodingchallenge.movie_db.ServiceBuilder

class MoviesRepository {
    private val apiService = ServiceBuilder.buildService(MovieDbEndpoints::class.java)

    fun getMovies() = apiService.getMovies(Base.apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getMovieDetails(id: Int) = apiService.getMovieDetails(id, Base.apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}