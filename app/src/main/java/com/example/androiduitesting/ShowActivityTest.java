package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<>(MainActivity.class);

    // 1️⃣ Check whether the activity correctly switched
    @Test
    public void testActivitySwitch() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(
                androidx.test.espresso.action.ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list to open ShowActivity
        onView(withText("Edmonton")).perform(click());

        // Verify ShowActivity screen is displayed
        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    // 2️⃣ Test whether the city name is consistent
    @Test
    public void testCityNameConsistency() {
        // Add and click city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(
                androidx.test.espresso.action.ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Calgary")).perform(click());

        // Check the same city name is displayed in ShowActivity
        onView(withText("Calgary")).check(matches(isDisplayed()));
    }

    // 3️⃣ Test the back button
    @Test
    public void testBackButton() {
        // Add and click city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(
                androidx.test.espresso.action.ViewActions.typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Toronto")).perform(click());

        // Click on back button in ShowActivity
        onView(withId(R.id.button_back)).perform(click());

        // Verify we returned to MainActivity (check Add button visible again)
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}
