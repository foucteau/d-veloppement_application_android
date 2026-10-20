package fr.laboratoire.compteursimple;

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
public class CounterUiTest {
    @Rule public ActivityScenarioRule<MainActivity> activity = new ActivityScenarioRule<>(MainActivity.class);
    private void tap(String label) { onView(withContentDescription(label)).perform(click()); }

    @Test public void incrementAndDecrement() {
        tap("Remettre à zéro"); tap("+1"); tap("+1"); tap("-1");
        onView(withId(R.id.counter_value)).check(matches(withText("1")));
    }

    @Test public void reset() {
        tap("Remettre à zéro"); tap("+1"); tap("Remettre à zéro");
        onView(withId(R.id.counter_value)).check(matches(withText("0")));
    }
}
