package jp.ac.meijou.androidsample.lesson1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Lesson1Activity の UI テスト（Instrumented Test）。
 * 実際の端末またはエミュレータ上で実行されます。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson1ActivityTest {

    // テスト対象のアクティビティを起動するためのルール
    @Rule
    public ActivityScenarioRule<Lesson1Activity> activityRule =
            new ActivityScenarioRule<>(Lesson1Activity.class);

    @Test
    public void testTextViewDisplaysCorrectText() {
        // IDが lesson1_text の TextView が "Hello Lesson1" というテキストを表示していることを検証する
        onView(withId(R.id.lesson1_text))
                .check(matches(withText("Hello Lesson1")));
    }
}
