package jp.ac.meijou.androidsample.lesson5;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * PrefDataStore の Unit テスト（Local Unit Test with Robolectric）。
 * <p>
 * DataStore は内部的に Context を必要とするため、Robolectricを使用して
 * JVM上で Context を提供し、テストを実行します。
 */
@RunWith(RobolectricTestRunner.class)
public class PrefDataStoreTest {

    private PrefDataStore dataStore;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dataStore = PrefDataStore.getInstance(context);
    }

    @Test
    public void testSetAndGetString() {
        String testKey = "test_key";
        String testValue = "test_value_" + System.currentTimeMillis();

        // 値を保存する
        dataStore.setString(testKey, testValue);

        // DataStore の保存は非同期で行われるため、少し待機する
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 値を取得して検証する
        Optional<String> result = dataStore.getString(testKey);

        assertTrue("値が存在すること", result.isPresent());
        assertEquals("保存した値と取得した値が一致すること", testValue, result.get());
    }
}
