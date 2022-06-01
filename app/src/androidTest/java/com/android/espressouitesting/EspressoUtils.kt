package com.android.espressouitesting

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher


object EspressoUtils {
    fun withRecyclerView(id: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(id)
    }

    fun withHint(hintRes: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(item: TextInputLayout): Boolean {
                val hint = item.hint ?: return false
                val hintString = item.context.getString(hintRes)
                return hint.toString().equals(hintString, ignoreCase = true)
            }
        }
    }

    fun withStrokeColor(strokeColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(item: TextInputLayout): Boolean {
                val context = item.context
                val boxStrokeColor = item.boxStrokeColor
                val color = context.getColor(strokeColor)
                return color == boxStrokeColor
            }
        }
    }

    fun withError(errorRes: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(item: TextInputLayout): Boolean {
                val context = item.context
                val error = item.error ?: return false
                val errorString = context.getString(errorRes)
                return error.toString().equals(errorString, ignoreCase = true)
            }
        }
    }

    fun onViewWait(viewMatcher: Matcher<View>, timeout: Int = 10): ViewInteraction {
        val waitPerTry = 300
        val maxTries = timeout * 1000 / waitPerTry
        for (i in 1..maxTries) {
            try {
                Espresso.onView(viewMatcher)
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                return Espresso.onView(viewMatcher)
            } catch (e: Throwable) {
                if (i == maxTries) {
                    throw e
                } else {
                    try {
                        Thread.sleep(waitPerTry.toLong())
                    } catch (ex: InterruptedException) {
                        ex.printStackTrace()
                    }
                }
            }
        }
        return Espresso.onView(viewMatcher)
    }
}