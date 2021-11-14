package com.ashwin.android.tmdbuitest.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.source.DummyMovies
import com.ashwin.android.tmdbuitest.data.source.RemoteMovieDataSource
import com.ashwin.android.tmdbuitest.factory.MovieFragmentFactory
import com.bumptech.glide.request.RequestOptions
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {
    @Test
    fun onOpen_movieData_visible_test() {
        val movie = DummyMovies.THE_RUNDOWN

        val movieDataSource = mockk<RemoteMovieDataSource>()
        every {
            movieDataSource.getMovie(movie.id)
        } returns movie

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)

        val fragmentFactory = MovieFragmentFactory(requestOptions, movieDataSource)

        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(movie.title)))

        Espresso.onView(ViewMatchers.withId(R.id.movie_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(movie.description)))
    }
}
