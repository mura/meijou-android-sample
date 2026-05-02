package jp.ac.meijou.androidsample.test;

import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Espresso 用のカスタム Matcher を提供するユーティリティクラス
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
}
