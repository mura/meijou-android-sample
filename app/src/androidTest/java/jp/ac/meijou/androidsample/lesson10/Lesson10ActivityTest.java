package jp.ac.meijou.androidsample.lesson10;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.test.Matchers;

/**
 * Lesson10Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson10ActivityTest {

    @Test
    public void testImageIsDisplayed() {
        try (ActivityScenario<Lesson10Activity> scenario = ActivityScenario.launch(Lesson10Activity.class)) {
            // 画像が非同期で取得されセットされるのを待機・検証
            onView(withId(R.id.lesson10_image)).perform(Matchers.waitUntil(Matchers.hasDrawable(), 5000));
            onView(withId(R.id.lesson10_image)).check(matches(Matchers.hasDrawable()));
        }
    }
}
