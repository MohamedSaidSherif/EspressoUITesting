package com.android.espressouitesting.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.espressouitesting.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun clickDirectors_navigateToDirectorsFragment() {
        //GIVEN
        val activityScenario = ActivityScenario.launch(MoviesActivity::class.java)

        //Navigate to DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        //THEN
        onView(withId(R.id.fragment_directors_parent))
            .check(matches(isDisplayed()))


        pressBack()

        //THEN
        onView(withId(R.id.fragment_movie_detail_parent))
            .check(matches(isDisplayed()))

        //Navigate to StarActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        //THEN
        onView(withId(R.id.fragment_star_actors_parent))
            .check(matches(isDisplayed()))
    }
}