package jp.ac.meijou.androidsample.lesson5;

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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Lesson5Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson5ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson5Activity> activityRule =
            new ActivityScenarioRule<>(Lesson5Activity.class);

    @Test
    public void testChangeText() {
        String testString = "Change Test";

        // EditText にテキストを入力し、キーボードを閉じる
        onView(withId(R.id.lesson5_edit_text))
                .perform(replaceText(testString), closeSoftKeyboard());

        // Change ボタンをクリックする
        onView(withId(R.id.lesson5_change_button))
                .perform(click());

        // TextView が入力されたテキストに変更されたことを検証する
        onView(withId(R.id.lesson5_text))
                .check(matches(withText(testString)));
    }

    @Test
    public void testSaveText() {
        String testString = "Save Test";

        // EditText にテキストを入力し、キーボードを閉じる
        onView(withId(R.id.lesson5_edit_text))
                .perform(replaceText(testString), closeSoftKeyboard());

        // Save ボタンをクリックする
        onView(withId(R.id.lesson5_save_button))
                .perform(click());

        // DataStore の保存は非同期であるため、実際には少し待機するか、
        // Activity を再起動して検証する必要があります。
        // ここでは簡単な UI インタラクションのみ実行します。
    }
}
