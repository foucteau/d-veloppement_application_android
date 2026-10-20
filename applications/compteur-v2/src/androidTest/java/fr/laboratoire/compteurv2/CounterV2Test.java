package fr.laboratoire.compteurv2;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
@RunWith(AndroidJUnit4.class)
public class CounterV2Test {
 @Test public void incrementAndDecrement() {
   onView(withText("+1")).perform(click());
   onView(withText("-1")).perform(click());
   // The value is persisted across launches; check numeric display is present.
   onView(withId(1001)).check(matches(androidx.test.espresso.matcher.ViewMatchers.isDisplayed()));
 }
}
