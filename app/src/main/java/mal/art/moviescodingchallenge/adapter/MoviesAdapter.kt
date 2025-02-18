package mal.art.moviescodingchallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mal.art.moviescodingchallenge.Base
import mal.art.moviescodingchallenge.R
import mal.art.moviescodingchallenge.model.Movie

class MoviesAdapter(
    private val moviesList: List<Movie>,
    private val clickListener: (Movie) -> Unit
    ) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>()
{
    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var moviePoster: ImageView = view.findViewById(R.id.movie_poster_image)
        var movieTitle: TextView = view.findViewById(R.id.movie_title)

        fun bind(movie: Movie, clickListener: (Movie) -> Unit) {
            Glide.with(itemView.context).load(Base.basePosterUrl + movie.poster_path).into(moviePoster)
            movieTitle.text = movie.title
            itemView.setOnClickListener { clickListener(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_list_recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       return holder.bind(moviesList[position], clickListener)
    }
}