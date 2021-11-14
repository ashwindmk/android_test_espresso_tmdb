package com.ashwin.android.tmdbuitest.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.data.source.DummyMovies
import com.ashwin.android.tmdbuitest.util.EspressoIdlingResource
//import com.ashwin.android.tmdbuitest.util.EspressoIdlingResourceTestRule
//import com.ashwin.android.tmdbuitest.util.EspressoIdlingResourceTestWatcher
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {
    @get:Rule  // This will launch a new activity before every test
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

//    @get:Rule
//    val espressoIdlingResourceRule = EspressoIdlingResourceTestRule()

//    @get:Rule
//    val espressoIdlingResourceTestWatcher = EspressoIdlingResourceTestWatcher()

    val LIST_ITEM = 4
    val MOVIE = DummyMovies.movies[LIST_ITEM]

    @Before
    fun setUp() {
        // Register idling resource
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun onLaunch_listFragment_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onItemSelected_detailFragment_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(LIST_ITEM, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE.title)))
    }

    @Test
    fun onItemSelected_onBackPressed_listFragment_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(LIST_ITEM, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE.title)))

        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onItemSelected_onClickDirectors_directorsFragment_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(LIST_ITEM, ViewActions.click()))

        // Navigate to DirectorsFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_directors_button))
            .perform(ViewActions.click())

        // Verify DirectorsFragment is visible
        Espresso.onView(ViewMatchers.withId(R.id.directors_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Verify directors list
        val directors = DummyMovies.movies[LIST_ITEM].directors
        Espresso.onView(ViewMatchers.withId(R.id.directors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        DirectorsFragment.stringBuilderForDirectors(directors!!)
                    )
                )
            )
    }

    @Test
    fun onItemSelected_onClickStarActors_starActorsFragment_visible_test() {
        // Verify MovieListFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Navigate to MovieDetailFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(LIST_ITEM, ViewActions.click()))

        // Verify MovieDetailFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(MOVIE.title)))

        // Navigate to StarActorsFragment
        Espresso.onView(ViewMatchers.withId(R.id.movie_star_actors_button))
            .perform(ViewActions.click())

        // Verify StarActorsFragment is visible
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Verify star actors list
        val starActors = DummyMovies.movies[LIST_ITEM].star_actors
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        StarActorsFragment.stringBuilderForStarActors(starActors!!)
                    )
                )
            )
    }

    @After
    fun tearDown() {
        // Unregister idling resource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}
