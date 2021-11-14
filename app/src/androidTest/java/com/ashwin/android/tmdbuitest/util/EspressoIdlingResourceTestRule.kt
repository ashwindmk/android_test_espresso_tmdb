package com.ashwin.android.tmdbuitest.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.Exception

class EspressoIdlingResourceTestRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return IdlingResourceStatement(base)
    }

    class IdlingResourceStatement(private val base: Statement?) : Statement() {
        override fun evaluate() {
            IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
            try {
                base?.evaluate() ?: throw Exception("Error evaluating test. Base statement is null.")
            } finally {
                IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
            }
        }
    }
}
