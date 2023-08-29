package jp.ac.meijou.androidsample.lesson5;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Optional;

import io.reactivex.rxjava3.core.Single;

/**
 * Lesson5: SharedPreferenceにアクセスするDataStore
 */
public class PrefDataStore {

    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    /**
     * コンストラクタ
     *
     * @param dataStore DataStore
     */
    private PrefDataStore(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Lesson5DataStore のインスタンスを返す
     *
     * @param context Context
     * @return Lesson5DataStoreのインスタンス(シングルトン)
     */
    public static PrefDataStore getInstance(Context context) {
        if (instance == null) {
            var dataStore = new RxPreferenceDataStoreBuilder(context.getApplicationContext(), "settings").build();
            instance = new PrefDataStore(dataStore);
        }
        return instance;
    }

    /**
     * Stringの値を取得する
     *
     * @param key 取得するキー
     * @return 取得したStringのOptional
     */
    public Optional<String> getString(String key) {
        return dataStore.data()
                .map(prefs -> {
                    var prefKey = PreferencesKeys.stringKey(key);
                    return Optional.ofNullable(prefs.get(prefKey));
                })
                .blockingFirst();
    }

    /**
     * Stringの値をを保存する
     *
     * @param key   保存するキー
     * @param value 保存する値
     */
    public void setString(String key, String value) {
        dataStore.updateDataAsync(prefsIn -> {
                    var mutablePreferences = prefsIn.toMutablePreferences();
                    var prefKey = PreferencesKeys.stringKey(key);
                    mutablePreferences.set(prefKey, value);
                    return Single.just(mutablePreferences);
                })
                .subscribe();
    }

    /**
     * 値を取得する(汎用版)
     *
     * @param key 取得するキー
     * @param <T> 取得する値の型
     * @return 取得した値のOptional
     */
    public <T> Optional<T> get(Preferences.Key<T> key) {
        return dataStore.data()
                .map(prefs -> Optional.ofNullable(prefs.get(key)))
                .blockingFirst();
    }

    /**
     * 値を保存する(汎用版)
     *
     * @param key   保存するキー
     * @param value 保存する値
     * @param <T>   保存する値の型
     */
    public <T> void set(Preferences.Key<T> key, T value) {
        dataStore.updateDataAsync(prefsIn -> {
                    var mutablePreferences = prefsIn.toMutablePreferences();
                    mutablePreferences.set(key, value);
                    return Single.just(mutablePreferences);
                })
                .subscribe();
    }
}
