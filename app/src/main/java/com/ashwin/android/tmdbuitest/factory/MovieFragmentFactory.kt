package com.ashwin.android.tmdbuitest.factory

import androidx.fragment.app.FragmentFactory
import com.ashwin.android.tmdbuitest.data.source.MovieDataSource
import com.ashwin.android.tmdbuitest.ui.DirectorsFragment
import com.ashwin.android.tmdbuitest.ui.MovieDetailFragment
import com.ashwin.android.tmdbuitest.ui.MovieListFragment
import com.ashwin.android.tmdbuitest.ui.StarActorsFragment
import com.bumptech.glide.request.RequestOptions

class MovieFragmentFactory(
    private val requestOptions: RequestOptions? = null,
    private val movieDataSource: MovieDataSource? = null
) : FragmentFactory() {
    private val TAG: String = "AppDebug"

    override fun instantiate(classLoader: ClassLoader, className: String) =
        when (className) {
            MovieListFragment::class.java.name -> {
                if (movieDataSource != null) {
                    MovieListFragment(movieDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            MovieDetailFragment::class.java.name -> {
                if (requestOptions != null && movieDataSource != null) {
                    MovieDetailFragment(requestOptions, movieDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}
