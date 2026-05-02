package jp.ac.meijou.androidsample.lesson8;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Lesson8SecondActivity の UI テスト（Instrumented Test）。
 */
@RunWith(AndroidJUnit4.class)
public class Lesson8SecondActivityTest {

    @Test
    public void testTextFromIntentIsDisplayed() {
        // Intent に文字列を付与して起動し、TextView に表示されるかを検証
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, Lesson8SecondActivity.class);
        intent.putExtra("text", "Test Passed");

        try (ActivityScenario<Lesson8SecondActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.lesson8_second_text)).check(matches(withText("Test Passed")));
        }
    }

    @Test
    public void testOkButtonFinishesWithResultOk() {
        // OKボタンをクリックすると RESULT_OK と付随データが返されて終了するか検証
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, Lesson8SecondActivity.class);

        try (ActivityScenario<Lesson8SecondActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.lesson8_second_button_1)).perform(click());

            assertNotNull(scenario.getResult());
            assertEquals(android.app.Activity.RESULT_OK, scenario.getResult().getResultCode());
            assertNotNull(scenario.getResult().getResultData());
            assertEquals("OK", scenario.getResult().getResultData().getStringExtra("ret"));
        }
    }

    @Test
    public void testCancelButtonFinishesWithResultCanceled() {
        // Cancelボタンをクリックすると RESULT_CANCELED が返されて終了するか検証
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, Lesson8SecondActivity.class);

        try (ActivityScenario<Lesson8SecondActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.lesson8_second_button_2)).perform(click());

            assertNotNull(scenario.getResult());
            assertEquals(android.app.Activity.RESULT_CANCELED, scenario.getResult().getResultCode());
        }
    }
}
