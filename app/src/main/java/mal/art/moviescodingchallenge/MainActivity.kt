package mal.art.moviescodingchallenge

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mal.art.moviescodingchallenge.adapter.MoviesAdapter
import mal.art.moviescodingchallenge.extensions.addFragment
import mal.art.moviescodingchallenge.fragment.MovieDetailsFragment
import mal.art.moviescodingchallenge.model.ListResponse
import mal.art.moviescodingchallenge.model.Movie
import mal.art.moviescodingchallenge.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel = ViewModelProvider.NewInstanceFactory().create(MoviesViewModel::class.java)

        observeViewModel()

        moviesViewModel.getMovies()
    }

    private fun observeViewModel() {
        moviesViewModel.subject.subscribe {
            when (it) {
                is MoviesViewModel.Event.LoadingFailure -> handleLoadingFailure(it.throwable)
                is MoviesViewModel.Event.LoadingMoviesSuccess -> handleLoadingSuccess(it.data)
            }
        }
    }

    private fun handleLoadingSuccess(data: ListResponse<Movie>) {
        movies_list_recycler_view.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = MoviesAdapter(data.results, { movie: Movie -> performMovieClick(movie)})
        }
    }

    private fun handleLoadingFailure(throwable: Throwable) {
        Log.d("ARTUR", throwable.printStackTrace().toString())
    }

    private fun performMovieClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt("movieId", movie.id)
        val fragment = MovieDetailsFragment()
        fragment.arguments = bundle
        fragment.addFragment(
            supportFragmentManager,
            R.id.movie_details_container,
            fragment,
            MovieDetailsFragment.TAG
        )
    }
}