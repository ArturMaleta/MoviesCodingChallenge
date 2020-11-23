package mal.art.moviescodingchallenge.model

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val overview: String,
    val poster_path: String
) {
}