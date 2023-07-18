package algonquin.cst2335.evan0285;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.core.AllOf.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test unit to test if password matches case requirements
     */
    @Test
    public void testFindMissingUpperCase(){

        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));

        appCompatEditText.perform(replaceText("password123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));

        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testMissingLowerCase(){

        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));

        appCompatEditText.perform(replaceText("PASSWORD123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));

        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testMissingDigit(){

        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));

        appCompatEditText.perform(replaceText("passWithoutDigit"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));

        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testMissingSpecialChar(){

        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));

        appCompatEditText.perform(replaceText("PassWithoutSpecial"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));

        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testCorrectTest(){

        ViewInteraction appCompatEditText = onView( withId(R.id.editTextText));

        appCompatEditText.perform(replaceText("Password123123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));

        textView.check(matches(withText("Your password meets the requirements")));
    }




    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
