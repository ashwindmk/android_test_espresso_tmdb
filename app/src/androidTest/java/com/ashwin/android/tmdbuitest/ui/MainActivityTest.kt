package com.ashwin.android.tmdbuitest.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun onLaunch_visible_test() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.main_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunch_title_nextButton_visible_test() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.title_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // OR we can use withEffectiveVisibility(Visibility) in place of isDisplayed()
        // Not sure which to use when!
        Espresso.onView(ViewMatchers.withId(R.id.next_button))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun onLaunch_titleText_matches_test() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.title_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.main_activity_title)))

        // OR we can use string directly
        Espresso.onView(ViewMatchers.withId(R.id.title_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText("MainActivity")))
    }

    @Test
    fun onNextClick_navigateTo_NextActivity_test() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.next_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.next_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onBackPressed_navigateTo_MainActivity_test() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.next_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.next_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

//        Espresso.onView(ViewMatchers.withId(R.id.back_button)).perform(ViewActions.click())
        // OR
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.main_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
