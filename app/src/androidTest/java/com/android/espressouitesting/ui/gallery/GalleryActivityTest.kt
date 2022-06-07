package com.android.espressouitesting.ui.gallery

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.android.espressouitesting.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class GalleryActivityTest{

//    @get: Rule
//    val intentsTestRule = IntentsTestRule(GalleryActivity::class.java)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(GalleryActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun test_validateIntentSentToPickPackage() {
        //WHEN
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )
        val activityResult = createGalleryPickActivityResultSub()
        intending(expectedIntent).respondWith(activityResult)

        //Execute
        onView(withId(R.id.button_open_gallery)).perform(click())

        //THEN
        intending(expectedIntent)
    }

    private fun createGalleryPickActivityResultSub(): Instrumentation.ActivityResult {
        val resource: Resources = InstrumentationRegistry.getInstrumentation().context.resources
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resource.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceEntryName(R.drawable.ic_launcher_background)
        )
        val resultIntent = Intent()
        resultIntent.data = imageUri
        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
    }
}