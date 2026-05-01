package jp.ac.meijou.androidsample.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.lesson1.Lesson1Activity;

/**
 * MainActivity の UI テスト（Instrumented Test）。
 *
 * <p>RecyclerView にレッスンリストが表示されることと、
 * 各アイテムをクリックすると対応する Activity に遷移することを検証します。</p>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    /**
     * RecyclerView が表示されていることを検証する。
     */
    @Test
    public void testRecyclerViewIsDisplayed() {
        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()));
    }

    /**
     * リストの先頭に "Lesson1" が表示されていることを検証する。
     */
    @Test
    public void testFirstItemIsLesson1() {
        onView(withId(R.id.recyclerView))
                .check(matches(hasDescendant(withText("Lesson1"))));
    }

    /**
     * リストの末尾（Lesson12）までスクロールできることを検証する。
     */
    @Test
    public void testScrollToLastItem() {
        // 15件分（0-indexed: 14）スクロールして最後のアイテムが表示されることを確認
        onView(withId(R.id.recyclerView))
                .perform(scrollToPosition(14));

        onView(withId(R.id.recyclerView))
                .check(matches(hasDescendant(withText("Lesson12"))));
    }

    /**
     * "Lesson1" をクリックすると Lesson1Activity に遷移することを検証する。
     */
    @Test
    public void testClickLesson1NavigatesToLesson1Activity() {
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, click()));

        Intents.intended(IntentMatchers.hasComponent(Lesson1Activity.class.getName()));
    }
}
