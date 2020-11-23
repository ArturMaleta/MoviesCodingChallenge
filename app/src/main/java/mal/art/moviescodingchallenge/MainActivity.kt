package mal.art.moviescodingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import mal.art.moviescodingchallenge.adapter.MoviesAdapter
import mal.art.moviescodingchallenge.model.Movie
import mal.art.moviescodingchallenge.model.PlayedMovies
import mal.art.moviescodingchallenge.movie_db.MovieDbEndpoints
import mal.art.moviescodingchallenge.movie_db.ServiceBuilder
import mal.art.moviescodingchallenge.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val request = ServiceBuilder.buildService(MovieDbEndpoints::class.java)
    private val call = request.getMovies(Base.apiKey)
    private val repository = MoviesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        call.enqueue(object : Callback<PlayedMovies> {
            override fun onResponse(call: Call<PlayedMovies>, response: Response<PlayedMovies>) {
                if (response.isSuccessful) {
                    movies_list_recycler_view.apply {
                        layoutManager = GridLayoutManager(this.context, 2)
                        adapter = MoviesAdapter(repository.getCall())
                    }
                }
            }

            override fun onFailure(call: Call<PlayedMovies>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}