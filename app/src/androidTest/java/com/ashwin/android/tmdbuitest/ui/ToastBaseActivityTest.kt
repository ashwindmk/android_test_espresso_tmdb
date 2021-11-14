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
class ToastBaseActivityTest {
    @Test
    fun validate_showToast_test() {
        val activityScenario = ActivityScenario.launch(ToastBaseActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.show_toast_button))
            .perform(ViewActions.click())

        // For some reason, this fails with api 31.
        // Issue link: https://github.com/android/android-test/issues/803
        Espresso.onView(ViewMatchers.withText(R.string.message))
            .inRoot(ToastMatcher())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
