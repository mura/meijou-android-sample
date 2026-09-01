package jp.ac.meijou.androidsample.lesson10;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.test.Matchers;

/**
 * Lesson10AdvancedActivity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson10AdvancedActivityTest {

    @Test
    public void testFetchImageWithText() {
        try (ActivityScenario<Lesson10AdvancedActivity> scenario = ActivityScenario.launch(Lesson10AdvancedActivity.class)) {
            // EditTextに入力してボタンを押す
            onView(withId(R.id.lesson10_adv_edit_text))
                    .perform(replaceText("test"), closeSoftKeyboard());
            onView(withId(R.id.lesson10_adv_get_button)).perform(click());

            // 画像が非同期で取得されセットされるのを待機・検証
            onView(withId(R.id.lesson10_adv_image)).perform(Matchers.waitUntil(Matchers.hasDrawable(), 5000));
            onView(withId(R.id.lesson10_adv_image)).check(matches(Matchers.hasDrawable()));
        }
    }
}
