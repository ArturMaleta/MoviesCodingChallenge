package mal.art.moviescodingchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mal.art.moviescodingchallenge.repository.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private var list: ArrayList<String> = arrayListOf()
    private val repository = MoviesRepository()
//
//    fun getMovies() {
//        repository.getCall()
//    }
}