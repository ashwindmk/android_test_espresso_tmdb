package com.ashwin.android.tmdbuitest.ui

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.After
import org.junit.Test
import android.app.Activity

import android.content.Intent

import android.app.Instrumentation

import android.graphics.BitmapFactory

import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.ashwin.android.tmdbuitest.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

@RunWith(AndroidJUnit4ClassRunner::class)
class CameraIntentActivityTest {
    @get:Rule
    val activityScenarioRule: ActivityScenarioRule<CameraIntentActivity> = ActivityScenarioRule(CameraIntentActivity::class.java)

    @Before
    fun setUp() {
        // Initializes Intents and begins recording intents.
        Intents.init()
    }

    @Test
    fun validate_intentToCaptureImage_test() {
        val expectedIntent: Matcher<Intent> = IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        val expectedResult = createImageCaptureActivityResultStub()

        Intents.intending(expectedIntent).respondWith(expectedResult)

        // Verify the ImageView has no image initially
        Espresso.onView(ViewMatchers.withId(R.id.image_view))
            .check(ViewAssertions.matches(CoreMatchers.not(ImageViewHasDrawableMatcher.hasDrawable())))

        Espresso.onView(ViewMatchers.withId(R.id.open_camera_button))
            .perform(ViewActions.click())

        Intents.intended(expectedIntent)

        // Verify the ImageView has no image after action
        Espresso.onView(ViewMatchers.withId(R.id.image_view))
            .check(ViewAssertions.matches(ImageViewHasDrawableMatcher.hasDrawable()))
    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult? {
        // Put the drawable in a bundle.
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA,
            BitmapFactory.decodeResource(
                InstrumentationRegistry.getInstrumentation().targetContext.resources,
                R.drawable.ic_launcher_background
            )
        )

        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)

        // Create the ActivityResult with the Intent.
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }

    @After
    fun tearDown() {
        // Clears Intents state.
        Intents.release()
    }
}
