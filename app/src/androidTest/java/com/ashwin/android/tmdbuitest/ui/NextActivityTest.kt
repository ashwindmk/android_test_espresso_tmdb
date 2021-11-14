package com.ashwin.android.tmdbuitest.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashwin.android.tmdbuitest.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NextActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(NextActivity::class.java)

    @Test
    fun onLaunch_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.next_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunch_title_backButton_visible_test() {
        Espresso.onView(ViewMatchers.withId(R.id.title_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.back_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onLaunch_titleText_matches_test() {
        Espresso.onView(ViewMatchers.withId(R.id.title_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.next_activity_title)))
    }
}
