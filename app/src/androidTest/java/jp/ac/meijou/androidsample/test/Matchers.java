package jp.ac.meijou.androidsample.test;

import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.util.HumanReadables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

/**
 * Espresso 用のカスタム Matcher および ViewAction を提供するユーティリティクラス
 */
public class Matchers {

    /**
     * ImageView に何らかの Drawable（画像）がセットされているかを検証する Matcher
     *
     * @return Matcher<View>
     */
    public static Matcher<View> hasDrawable() {
        return new BoundedMatcher<>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            protected boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }

    /**
     * 指定された Matcher が満たされるまで待機する ViewAction
     *
     * @param matcher       満たされるべき条件
     * @param timeoutMillis タイムアウト（ミリ秒）
     * @return ViewAction
     */
    public static ViewAction waitUntil(Matcher<View> matcher, long timeoutMillis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return org.hamcrest.Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "wait until matcher is satisfied within " + timeoutMillis + "ms";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                long startTime = System.currentTimeMillis();
                long endTime = startTime + timeoutMillis;

                while (System.currentTimeMillis() < endTime) {
                    if (matcher.matches(view)) {
                        return;
                    }
                    uiController.loopMainThreadForAtLeast(100);
                }

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
