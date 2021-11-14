package com.ashwin.android.tmdbuitest.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.util.EspressoIdlingResourceTestWatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {
    @get:Rule
    val espressoIdlingResourceTestWatcher = EspressoIdlingResourceTestWatcher()

    @Test
    fun onLaunch_movieDetailFragment_visible_test() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)

        // Navigate to MovieDetailFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(0, ViewActions.click()))

        // Navigate to DirectorsFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_directors_button))
            .perform(ViewActions.click())

        // Verify DirectorsFragment is visible
        Espresso.onView(ViewMatchers.withId(R.id.directors_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Navigate back to MovieDetailFragment
        Espresso.pressBack()

        // Verify MovieDetailFragment is visible
        Espresso.onView(ViewMatchers.withId(R.id.movie_detail_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Navigate to StarActorsFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_star_actors_button))
            .perform(ViewActions.click())

        // Verify StarActorsFragment is visible
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
