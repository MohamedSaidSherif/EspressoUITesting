package com.android.espressouitesting.ui.dialog

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.espressouitesting.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DialogActivityTest {

    @Test
    fun test_showDialog_captureNameInput() {
        //WHEN
        val activityScenario = ActivityScenario.launch(DialogActivity::class.java)
        val EXPECTED_NAME = "TEST"

        //EXECUTE
        onView(withId(R.id.button_launch_dialog)).perform(click())

        //THEN
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        //EXECUTE
        onView(withText(R.string.text_ok)).perform(click())

        //THEN
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        //ENTER NAME INPUT
        onView(withId(com.afollestad.materialdialogs.input.R.id.md_input_message))
            .perform(typeText(EXPECTED_NAME))

        //EXECUTE
        onView(withText(R.string.text_ok)).perform(click())

        //THEN
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))
    }
}