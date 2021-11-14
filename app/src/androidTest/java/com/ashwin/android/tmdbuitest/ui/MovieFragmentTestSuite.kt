package com.ashwin.android.tmdbuitest.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailFragmentTest::class,
    DirectorsFragmentTest::class,
    MovieNavigationTest::class,
    MovieListFragmentTest::class
)
class MovieFragmentTestSuite
