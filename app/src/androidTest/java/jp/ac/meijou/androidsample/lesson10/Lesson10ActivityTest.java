package jp.ac.meijou.androidsample.lesson10;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedConstruction;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.test.Matchers;
import jp.ac.meijou.androidsample.test.MockBitmap;
import jp.ac.meijou.androidsample.test.MockOkHttp;
import okhttp3.OkHttpClient;

@RunWith(AndroidJUnit4.class)
public class Lesson10ActivityTest {

    private MockedConstruction<OkHttpClient> mockOkHttpClient;

    @Before
    public void setUp() {
        mockOkHttpClient = MockOkHttp.mock(request -> {
            byte[] bitmapdata = MockBitmap.createBitmapByteArray();
            return MockOkHttp.createMockResponse(request, 200, "image/png", bitmapdata);
        });
    }

    @After
    public void tearDown() {
        if (mockOkHttpClient != null) {
            mockOkHttpClient.close();
        }
    }

    @Test
    public void testImageIsDisplayed() {
        try (ActivityScenario<Lesson10Activity> scenario = ActivityScenario.launch(Lesson10Activity.class)) {
            // 画像がセットされているか（Drawableがnullでないか）を確認
            onView(withId(R.id.lesson10_image)).check(matches(Matchers.hasDrawable()));
        }
    }
}
