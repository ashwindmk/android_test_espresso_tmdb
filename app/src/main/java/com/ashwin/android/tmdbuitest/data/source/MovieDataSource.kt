package com.ashwin.android.tmdbuitest.data.source

import com.ashwin.android.tmdbuitest.data.model.Movie

interface MovieDataSource {
    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}
