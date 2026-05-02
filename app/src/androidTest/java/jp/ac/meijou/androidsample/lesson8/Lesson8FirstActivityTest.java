package jp.ac.meijou.androidsample.lesson8;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

/**
 * Lesson8FirstActivity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson8FirstActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson8FirstActivity> activityRule =
            new ActivityScenarioRule<>(Lesson8FirstActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testExplicitIntent() {
        // ボタン1をクリックして明示的Intentが発行されたかを検証
        onView(withId(R.id.lesson8_first_button_1)).perform(click());

        intended(hasComponent(Lesson8SecondActivity.class.getName()));
    }

    @Test
    public void testImplicitIntentCamera() {
        // カメラボタンをクリックしてカメラアプリ起動用のIntentが発行されたかを検証
        onView(withId(R.id.lesson8_camera_button)).perform(click());

        intended(hasAction(android.provider.MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA));
    }

    @Test
    public void testIntentWithExtra() {
        // 文字入力後にボタン3をクリックし、データ付きのIntentが発行されたかを検証
        String testText = "Hello Intent";
        onView(withId(R.id.lesson8_first_edit_text))
                .perform(replaceText(testText), closeSoftKeyboard());

        onView(withId(R.id.lesson8_first_button_3)).perform(click());

        intended(allOf(
                hasComponent(Lesson8SecondActivity.class.getName()),
                hasExtra("text", testText)
        ));
    }
}
