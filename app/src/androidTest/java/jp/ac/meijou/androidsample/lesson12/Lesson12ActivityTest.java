package jp.ac.meijou.androidsample.lesson12;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class Lesson12ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson12Activity> activityRule =
            new ActivityScenarioRule<>(Lesson12Activity.class);

    @Test
    public void testDateButtonUpdatesCalendar() {
        // ボタンをタップしてクラッシュしないことを確認（内部で2023/1/1にセットされる）
        onView(withId(R.id.dateButton)).perform(click());
        
        // CalendarViewが表示されたままであることを確認
        onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }
}
