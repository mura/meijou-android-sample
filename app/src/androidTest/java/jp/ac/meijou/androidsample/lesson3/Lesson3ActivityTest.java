package jp.ac.meijou.androidsample.lesson3;

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
 * Lesson3Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson3ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson3Activity> activityRule =
            new ActivityScenarioRule<>(Lesson3Activity.class);

    @Test
    public void testButtonClickChangesText() {
        // ボタンをクリックする
        onView(withId(R.id.lesson3_button))
                .perform(click());

        // TextView が "Hello Lesson3" というテキストに変更されたことを検証する
        onView(withId(R.id.lesson3_text))
                .check(matches(withText("Hello Lesson3")));
    }
}
