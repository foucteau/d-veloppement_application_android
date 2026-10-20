package fr.laboratoire.calculatricetest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class CalculatorUiTest {
    @Rule public ActivityScenarioRule<MainActivity> activity = new ActivityScenarioRule<>(MainActivity.class);
    private void tap(String key) { onView(withContentDescription("Touche " + key)).perform(click()); }

    @Test public void additionWorks() {
        tap("2"); tap("+"); tap("3"); tap("=");
        onView(withId(R.id.result_view)).check(matches(withText("5")));
    }

    @Test public void divisionByZeroShowsError() {
        tap("8"); tap("÷"); tap("0"); tap("=");
        onView(withId(R.id.result_view)).check(matches(withText("Erreur")));
    }

    @Test public void clearResetsDisplay() {
        tap("9"); tap("C");
        onView(withId(R.id.result_view)).check(matches(withText("0")));
    }
}
