package jp.ac.meijou.androidsample.lesson9;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

/**
 * Lesson9Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson9ActivityTest {

    @Test
    public void testGistContentIsDisplayed() {
        try (ActivityScenario<Lesson9Activity> scenario = ActivityScenario.launch(Lesson9Activity.class)) {
            // 通信が完了して TextView に何らかの文字列が表示されるのを検証
            onView(withId(R.id.lesson9_text)).check(matches(isDisplayed()));
        }
    }
}
