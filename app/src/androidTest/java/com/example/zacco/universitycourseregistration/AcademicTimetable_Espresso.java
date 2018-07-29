package com.example.zacco.universitycourseregistration;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/* TODO */

@RunWith(AndroidJUnit4.class)
public class AcademicTimetable_Espresso {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(AcademicTimetable.class);
    @Before
    public void initValidAndInvalidString(){
        Intents.init();

    }

    @Test
    public void FilterFallSemester() throws InterruptedException {
        onView(withId(R.id.term)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fall 2019"))).perform(click());
        onView(withId(R.id.term)).check(matches(withSpinnerText(containsString("Fall 2019"))));

        onView(withId(R.id.filterButton)).perform(click());
    }

    @Test
    public void FilterMathDepartment() throws InterruptedException {
        onView(withId(R.id.spec)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Mathematics"))).perform(click());
        onView(withId(R.id.spec)).check(matches(withSpinnerText(containsString("Mathematics"))));

        onView(withId(R.id.filterButton)).perform(click());
    }

    @Test
    public void FilterGeoDepartment() throws InterruptedException {
        onView(withId(R.id.spec)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Geology"))).perform(click());
        onView(withId(R.id.spec)).check(matches(withSpinnerText(containsString("Geology"))));

        onView(withId(R.id.filterButton)).perform(click());
    }

    @Test
    public void FilterWinterAndEnglish() throws InterruptedException {
        onView(withId(R.id.term)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Winter 2019"))).perform(click());
        onView(withId(R.id.term)).check(matches(withSpinnerText(containsString("Winter 2019"))));

        onView(withId(R.id.spec)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("English"))).perform(click());
        onView(withId(R.id.spec)).check(matches(withSpinnerText(containsString("English"))));

        onView(withId(R.id.filterButton)).perform(click());
    }

    @After
    public void release(){
        Intents.release();
    }
}