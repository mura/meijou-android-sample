package jp.ac.meijou.androidsample.lesson5;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Optional;

import io.reactivex.rxjava3.core.Single;

/**
 * Lesson5: SharedPreferenceにアクセスするDataStore
 */
public class Lesson5DataStore {

    private static Lesson5DataStore instance;

    private final RxDataStore<Preferences> dataStore;

    /**
     * コンストラクタ
     *
     * @param dataStore DataStore
     */
    private Lesson5DataStore(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Lesson5DataStore のインスタンスを返す
     *
     * @param context Context
     * @return Lesson5DataStoreのインスタンス(シングルトン)
     */
    public static Lesson5DataStore getInstance(Context context) {
        if (instance == null) {
            var dataStore = new RxPreferenceDataStoreBuilder(context.getApplicationContext(), "settings").build();
            instance = new Lesson5DataStore(dataStore);
        }
        return instance;
    }

    /**
     * 値を取得する
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
     * 値を保存する
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
