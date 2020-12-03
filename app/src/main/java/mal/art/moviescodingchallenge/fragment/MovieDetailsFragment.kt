package mal.art.moviescodingchallenge.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_details_fragment_layout.*
import mal.art.moviescodingchallenge.Base
import mal.art.moviescodingchallenge.R
import mal.art.moviescodingchallenge.model.Movie
import mal.art.moviescodingchallenge.viewmodel.MoviesViewModel

class MovieDetailsFragment: Fragment(R.layout.movie_details_fragment_layout) {
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel = ViewModelProvider.NewInstanceFactory().create(MoviesViewModel::class.java)

        val bundle = arguments
        observeViewModel()
        moviesViewModel.getMovieDetails(bundle!!.getInt("movieId"))
    }

    private fun observeViewModel() {
        moviesViewModel.subject.subscribe {
            when (it) {
                is MoviesViewModel.Event.LoadingFailure -> handleLoadingFailure(it.throwable)
                is MoviesViewModel.Event.LoadingMovieDetailsSuccess -> handleLoadingSuccess(it.movie)
            }
        }
    }

    private fun handleLoadingSuccess(movie: Movie) {
        Log.d("ARTUR", movie.title)

        loadMoviePoster(movie)
        loadMovieDetails(movie)


    }

    private fun loadMovieDetails(movie: Movie) {
        movie_details_title_tv.apply {
            text = movie.title
        }

        movie_details_rating_tv.apply {
            text = movie.vote_average.toString()
        }

        movie_details_release_date_tv.apply {
            text = movie.release_date
        }

        movie_details_overview_tv.apply {
            text = movie.overview
        }

    }

    private fun loadMoviePoster(movie: Movie) {
        Glide.with(this).load(Base.basePosterUrl + movie.poster_path)
            .into(movie_details_poster_image)
    }

    private fun handleLoadingFailure(throwable: Throwable) {
        throwable.printStackTrace()
    }

    companion object {
        const val TAG = "MOVIE_DETAILS_FLAG"
    }
}