package mal.art.moviescodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject
import mal.art.moviescodingchallenge.model.ListResponse
import mal.art.moviescodingchallenge.model.Movie
import mal.art.moviescodingchallenge.repository.MoviesRepository

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()
    val subject = PublishSubject.create<Event>()

    sealed class Event {
        data class LoadingFailure(val throwable: Throwable): Event()
        data class LoadingMoviesSuccess(val data: ListResponse<Movie>): Event()
        data class LoadingMovieDetailsSuccess(val movie: Movie): Event()
    }

    fun getMovies() {
        repository.getMovies()
            .subscribe({ subject.onNext(Event.LoadingMoviesSuccess(it)) }, { subject.onNext(Event.LoadingFailure(it)) })
    }

    fun getMovieDetails(id: Int) {
        repository.getMovieDetails(id)
            .subscribe({ subject.onNext(Event.LoadingMovieDetailsSuccess(it)) }, { subject.onNext(Event.LoadingFailure(it)) })
    }
}