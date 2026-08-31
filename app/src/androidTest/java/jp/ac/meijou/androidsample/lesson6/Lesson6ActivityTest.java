package jp.ac.meijou.androidsample.lesson6;

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
 * Lesson6Activity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson6ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson6Activity> activityRule = new ActivityScenarioRule<>(Lesson6Activity.class);

    @Test
    public void testCheckBoxChangesText() {
        // CheckBox をクリックしてテキストが変更されたことを確認
        onView(withId(R.id.lesson6_check_box)).perform(click());
        onView(withId(R.id.lesson6_text)).check(matches(withText("check: true")));
    }

    @Test
    public void testRadioButtonChangesText() {
        // RadioButton をクリックしてテキストが変更されたことを確認
        onView(withId(R.id.lesson6_radio1)).perform(click());
        onView(withId(R.id.lesson6_text)).check(matches(withText("radio: ラジオボタン1")));
    }

    @Test
    public void testSwitchChangesText() {
        // Switch をクリックしてテキストが変更されたことを確認
        onView(withId(R.id.lesson6_switch)).perform(click());
        onView(withId(R.id.lesson6_text)).check(matches(withText("switch: true")));
    }

    @Test
    public void testToggleButtonChangesText() {
        // ToggleButton をクリックしてテキストが変更されたことを確認
        onView(withId(R.id.lesson6_toggle)).perform(click());
        onView(withId(R.id.lesson6_text)).check(matches(withText("toggle: true")));
    }

    @Test
    public void testImageButtonAndFabClick() {
        // ImageButton と FAB がクリック可能でクラッシュしないことを確認
        onView(withId(R.id.lesson6_image_button)).perform(click());
        onView(withId(R.id.lesson6_fab)).perform(click());
    }

    @Test
    public void testSeekBarSwipe() {
        // SeekBar を操作してクラッシュしないことを確認
        onView(withId(R.id.lesson6_seek_bar)).perform(androidx.test.espresso.action.ViewActions.swipeRight());
    }
}
