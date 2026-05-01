package jp.ac.meijou.androidsample.lesson4;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Lesson4Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson4ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson4Activity> activityRule =
            new ActivityScenarioRule<>(Lesson4Activity.class);

    @Test
    public void testEditTextAndButtonClick() {
        String testString = "Espresso Test";

        // EditText にテキストを入力し、キーボードを閉じる
        onView(withId(R.id.lesson4_edit_text))
                .perform(replaceText(testString), closeSoftKeyboard());

        // ボタンをクリックする
        onView(withId(R.id.lesson4_button))
                .perform(click());

        // TextView が入力されたテキストに変更されたことを検証する
        onView(withId(R.id.lesson4_text))
                .check(matches(withText(testString)));
    }
}
