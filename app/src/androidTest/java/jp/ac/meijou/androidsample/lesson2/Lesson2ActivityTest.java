package jp.ac.meijou.androidsample.lesson2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Lesson2Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson2ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson2Activity> activityRule =
            new ActivityScenarioRule<>(Lesson2Activity.class);

    @Test
    public void testInitState() {
        // TextView が "Hello Lesson2" というテキストを表示していることを検証する
        onView(withId(R.id.lesson2_text))
                .check(matches(withText("Hello Lesson2")));

        // ImageView が表示されていることを検証する
        onView(withId(R.id.imageView))
                .check(matches(isDisplayed()));
    }
}
