package com.castrodev.shufflesongs.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import com.castrodev.shufflesongs.R
import org.junit.Rule
import org.junit.Test

@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkShuffleButtonVisibility() {
        onView(withId(R.id.action_shuffle))
            .check(matches(isDisplayed()))
    }
}