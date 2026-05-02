package jp.ac.meijou.androidsample.lesson9;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedConstruction;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.test.MockOkHttp;
import okhttp3.OkHttpClient;

@RunWith(AndroidJUnit4.class)
public class Lesson9ActivityTest {

    private MockedConstruction<OkHttpClient> mockOkHttpClient;

    @Before
    public void setUp() {
        mockOkHttpClient = MockOkHttp.mock(request -> {
            String mockJsonResponse = "{\"files\": {\"OkHttp.txt\": {\"content\": \"Mocked Gist Content\"}}}";
            return MockOkHttp.createMockResponse(request, 200, "application/json", mockJsonResponse);
        });
    }

    @After
    public void tearDown() {
        if (mockOkHttpClient != null) {
            mockOkHttpClient.close();
        }
    }

    @Test
    public void testGistContentIsDisplayed() {
        try (ActivityScenario<Lesson9Activity> scenario = ActivityScenario.launch(Lesson9Activity.class)) {
            // Activityが起動し、モックされた通信が行われUIが更新されるのを待つ
            onView(withId(R.id.lesson9_text)).check(matches(withText("Mocked Gist Content")));
        }
    }
}
