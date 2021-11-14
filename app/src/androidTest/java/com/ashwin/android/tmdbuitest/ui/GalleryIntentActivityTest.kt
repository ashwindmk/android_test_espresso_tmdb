package com.ashwin.android.tmdbuitest.ui

import android.app.Instrumentation
//import androidx.test.espresso.intent.rule.IntentsTestRule  // Deprecated
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.app.Activity

import android.content.Intent

import android.content.ContentResolver
import android.content.Intent.ACTION_PICK
import android.content.res.Resources

import android.net.Uri

import android.provider.MediaStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.ashwin.android.tmdbuitest.R
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.After

import org.junit.Before

@RunWith(AndroidJUnit4ClassRunner::class)
class GalleryIntentActivityTest {
//    @get:Rule
//    val intentsTestRule = IntentsTestRule(GalleryIntentActivity::class.java)  // Deprecated

    @get:Rule
    val mActivityScenarioRule: ActivityScenarioRule<GalleryIntentActivity> = ActivityScenarioRule(GalleryIntentActivity::class.java)

    @Before
    fun setUp() {
        // Initializes Intents and begins recording intents.
        Intents.init()
    }

    @Test
    fun validate_intentToPickImage_test() {
        val expectedIntent: Matcher<Intent> = Matchers.allOf(
            IntentMatchers.hasAction(ACTION_PICK),
            IntentMatchers.hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )

        val result = createImagePickActivityResultStub()

        // Stub the Intent.
//        Intents.intending(IntentMatchers.hasAction(Intent.ACTION_PICK)).respondWith(result)
        // OR
        Intents.intending(expectedIntent).respondWith(result)

        Espresso.onView(ViewMatchers.withId(R.id.open_gallery_button))
            .perform(ViewActions.click())

        Intents.intended(expectedIntent)
    }

    private fun createImagePickActivityResultStub(): Instrumentation.ActivityResult {
        val resources: Resources = InstrumentationRegistry.getInstrumentation().targetContext.resources
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background) + '/' +
                    resources.getResourceTypeName(R.drawable.ic_launcher_background) + '/' +
                    resources.getResourceEntryName(R.drawable.ic_launcher_background)
        )
        println("test: imageUri: $imageUri")
        val resultIntent = Intent()
        resultIntent.data = imageUri
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent)
    }

    @After
    fun tearDown() {
        // Clears Intents state.
        Intents.release()
    }
}
