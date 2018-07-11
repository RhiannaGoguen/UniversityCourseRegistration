package com.example.zacco.universitycourseregistration;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/* TODO */


@RunWith(AndroidJUnit4.class)
public class RegisteredClasses_Espresso {
    private ListView listView;
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(WelcomePage.class);

    public RegisteredClasses_Espresso() {
        listView = (ListView) listView.findViewById(R.id.courses);
    }

    @Before
    public void initValidAndInvalidString()
    {
        Intents.init();
    }

    @Test
    public void studentInfoIsDisplayed() {
        onView(withId(R.id.studentInfo)).check(matches(withId(R.id.studentInfo)));
        onView(withId(R.id.studentID)).check(matches(withId(R.id.studentID)));
    }

    @Test
    public void restaurantListViewPopulates() {
        assertNotNull(listView.getAdapter());
        assertEquals(listView.getAdapter().getCount(), listView.getAdapter().getCount());
    }

    @Test
    public void studentClassesAreDisplayed() {
        onView(withId(R.id.courses)).check(matches(withId(R.id.courses)));
    }


    @After
    public void release(){
        Intents.release();
    }
}