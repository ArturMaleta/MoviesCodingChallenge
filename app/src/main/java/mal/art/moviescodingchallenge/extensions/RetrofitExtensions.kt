package mal.art.moviescodingchallenge.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import retrofit2.Call
import retrofit2.Callback

fun <T> Call<T>.enqueuee(lifecycleOwner: LifecycleOwner, callback: Callback<T>) {
    lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun cancelCalls() {
            this@enqueuee.cancel()
        }
    })
    this.enqueue(callback)
}