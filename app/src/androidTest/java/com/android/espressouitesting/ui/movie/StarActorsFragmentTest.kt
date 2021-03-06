package com.android.espressouitesting.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.espressouitesting.R
import com.android.espressouitesting.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest {

    @Test
    fun test_actorsListDisplayed() {
        //GIVEN
        val startActors =  arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", startActors)

        //WHEN
        val scenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        //THEN
        onView(withId(R.id.star_actors_text))
            .check(matches(withText(StarActorsFragment.stringBuilderForStarActors(startActors))))
    }
}