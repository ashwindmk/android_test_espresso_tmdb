package com.ashwin.android.tmdbuitest.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.source.DummyMovies
import com.ashwin.android.tmdbuitest.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest {
    @Test
    fun directorsList_visible_test() {
        val directors = DummyMovies.THE_RUNDOWN.directors
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)

        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        Espresso.onView(ViewMatchers.withId(R.id.directors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        DirectorsFragment.stringBuilderForDirectors(directors!!)
                    )
                )
            )
    }
}
