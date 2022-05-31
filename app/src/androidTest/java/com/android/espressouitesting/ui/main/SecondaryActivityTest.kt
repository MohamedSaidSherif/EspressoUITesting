package com.android.espressouitesting.ui.main

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.espressouitesting.R
import org.junit.Rule
import org.junit.Test


class SecondaryActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(SecondaryActivity::class.java)

    @Test
    fun testActivity_inView() {
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))

        // Notice this does not effect the next test
        activityScenarioRule.scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun testVisibility_titleAndBackButton() {
        onView(withId(R.id.activity_secondary_title)).check(matches(isDisplayed())) //method 1

        onView(withId(R.id.activity_secondary_title))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE))) //method 2

        onView(withId(R.id.button_back)).check(matches(isDisplayed()))
    }

    @Test
    fun testTitleTextDisplayed() {
        onView(withId(R.id.activity_secondary_title))
            .check(matches(withText(R.string.text_secondary_activity)))
    }
}