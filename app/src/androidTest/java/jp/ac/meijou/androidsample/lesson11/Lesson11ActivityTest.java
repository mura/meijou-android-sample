package jp.ac.meijou.androidsample.lesson11;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.ac.meijou.androidsample.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class Lesson11ActivityTest {

    @Rule
    public ActivityScenarioRule<Lesson11Activity> activityRule =
            new ActivityScenarioRule<>(Lesson11Activity.class);

    @Test
    public void testNetworkInfoIsDisplayed() {
        // エミュレータや実機のネットワーク状態が取得され表示されるため、クラッシュせずにViewが表示されるかを確認する
        onView(withId(R.id.lesson11_transport_title)).check(matches(isDisplayed()));
        onView(withId(R.id.lesson11_transport)).check(matches(isDisplayed()));
        onView(withId(R.id.lesson11_ipaddress_title)).check(matches(isDisplayed()));
        onView(withId(R.id.lesson11_ipaddress)).check(matches(isDisplayed()));
    }
}
