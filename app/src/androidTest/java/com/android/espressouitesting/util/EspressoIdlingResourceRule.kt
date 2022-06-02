package com.android.espressouitesting.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Option1:
 * This option is much more difficult to read and is more verbose
 */
//class EspressoIdlingResourceRule : TestRule {
//
//    override fun apply(base: Statement, description: Description): Statement {
//        return IdlingResourceStatement(base)
//    }
//
//    class IdlingResourceStatement(private val base: Statement) : Statement() {
//
//        private val idlingResources = EspressoIdlingResource.countingIdlingResource
//
//        override fun evaluate() {
//            //Before
//            IdlingRegistry.getInstance().register(idlingResources)
//            try {
//                base.evaluate()
//            } finally {
//                //After
//                IdlingRegistry.getInstance().unregister(idlingResources)
//            }
//        }
//    }
//}

/**
 * Option2:
 * Simplified version of option#1. (TestWatcher class implements TestRule)
 */
class EspressoIdlingResourceRule: TestWatcher() {

    private val idlingResource = EspressoIdlingResource.countingIdlingResource

    //Before
    override fun starting(description: Description) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    //After
    override fun finished(description: Description) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}