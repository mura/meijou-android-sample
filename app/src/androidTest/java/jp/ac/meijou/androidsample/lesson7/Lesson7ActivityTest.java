package jp.ac.meijou.androidsample.lesson7;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Lesson7Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson7ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson7Activity> activityRule =
            new ActivityScenarioRule<>(Lesson7Activity.class);

    @Test
    public void testAddition() {
        // 1 + 2 = 3 の計算をテスト
        onView(withId(R.id.lesson7_button_1)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("1")));

        onView(withId(R.id.lesson7_button_plus)).perform(click());

        onView(withId(R.id.lesson7_button_2)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("2")));

        onView(withId(R.id.lesson7_button_equal)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("3")));
    }

    @Test
    public void testSubtraction() {
        // 5 - 2 = 3 の計算をテスト
        onView(withId(R.id.lesson7_button_5)).perform(click());
        onView(withId(R.id.lesson7_button_minus)).perform(click());
        onView(withId(R.id.lesson7_button_2)).perform(click());
        onView(withId(R.id.lesson7_button_equal)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("3")));
    }

    @Test
    public void testMultiplication() {
        // 3 * 4 = 12 の計算をテスト
        onView(withId(R.id.lesson7_button_3)).perform(click());
        onView(withId(R.id.lesson7_button_multiply)).perform(click());
        onView(withId(R.id.lesson7_button_4)).perform(click());
        onView(withId(R.id.lesson7_button_equal)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("12")));
    }

    @Test
    public void testDivision() {
        // 8 / 2 = 4 の計算をテスト
        onView(withId(R.id.lesson7_button_8)).perform(click());
        onView(withId(R.id.lesson7_button_divide)).perform(click());
        onView(withId(R.id.lesson7_button_2)).perform(click());
        onView(withId(R.id.lesson7_button_equal)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("4")));
    }

    @Test
    public void testAllClear() {
        // 5 入力後、ACで 0 になることをテスト
        onView(withId(R.id.lesson7_button_5)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("5")));

        onView(withId(R.id.lesson7_button_all_clear)).perform(click());
        onView(withId(R.id.lesson7_result)).check(matches(withText("0")));
    }
}
