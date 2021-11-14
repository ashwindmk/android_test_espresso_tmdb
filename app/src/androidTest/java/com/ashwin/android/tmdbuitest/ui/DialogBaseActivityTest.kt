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
class DialogBaseActivityTest {
    @Test
    fun showDialog_captureInput_test() {
        val activityScenario = ActivityScenario.launch(DialogBaseActivity::class.java)
        val expectedName = "Alice"

        Espresso.onView(ViewMatchers.withId(R.id.launch_dialog_button))
            .perform(ViewActions.click())

        // Verify if dialog is visible
        Espresso.onView(ViewMatchers.withText(R.string.enter_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click OK without entering name
        Espresso.onView(ViewMatchers.withText(R.string.ok))
            .perform(ViewActions.click())

        // Verify if dialog is still visible
        Espresso.onView(ViewMatchers.withText(R.string.enter_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Enter name
        Espresso.onView(ViewMatchers.withId(R.id.dialog_edit_text))
            .perform(ViewActions.typeText(expectedName))

        // Click OK after entering name
        Espresso.onView(ViewMatchers.withText(R.string.ok))
            .perform(ViewActions.click())

        // Verify if dialog is dismissed
        Espresso.onView(ViewMatchers.withText(R.string.enter_name))
            .check(ViewAssertions.doesNotExist())

        // Verify name is set
        Espresso.onView(ViewMatchers.withId(R.id.name_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedName)))
    }
}
