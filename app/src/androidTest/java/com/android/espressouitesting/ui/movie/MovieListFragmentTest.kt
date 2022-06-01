package com.android.espressouitesting.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.espressouitesting.EspressoUtils.onViewWait
import com.android.espressouitesting.EspressoUtils.withRecyclerView
import com.android.espressouitesting.R
import com.android.espressouitesting.data.FakeMovieData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get: Rule
    val activityScenarioRule = ActivityScenarioRule(MoviesActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    /**
     * RecyclerView Comes into view
     */
    @Test
    fun test_isMovieListFragmentDisplayed_onAppLaunch() {
        onViewWait(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select item, navigate to DetailFragment
     * Correct movie in view?
     */
    @Test
    fun test_selectListItem_isDetailFragmentDisplayed() {
        onViewWait(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()
                    )
            )

        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))
    }

    /**
     * Select item, navigate to DetailFragment
     * Press back
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {
        onViewWait(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()
                    )
            )

        onViewWait(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select item, navigate to DetailFragment
     * Select directors textview, navigate to DirectorsFragment
     */
    @Test
    fun test_navigateToDirectorsFragment_validateDirectorsList() {
        onViewWait(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()
                    )
            )

        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        val directors = DirectorsFragment.stringBuilderForDirectors(MOVIE_IN_TEST.directors!!)
        onView(withId(R.id.directors_text))
            .check(matches(withText(directors)))
    }

    /**
     * Select item, navigate to StarActorsFragment
     * Select star actors textview, navigate to StartActorsFragment
     */
    @Test
    fun test_navigateToStarActorsFragment_validateStarActorsList() {
        onViewWait(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()
                    )
            )

        onView(withId(R.id.movie_title))
            .check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        val starActors = StarActorsFragment.stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!)
        onView(withId(R.id.star_actors_text))
            .check(matches(withText(starActors)))
    }
}
