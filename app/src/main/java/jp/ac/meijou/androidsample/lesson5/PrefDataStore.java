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
 * <p>
 * Shared Preferencesの後継である Preference DataStore を利用したデータ保存クラスです。
 * Key-Value形式でデータを管理し、設定値など小さな値の保存に適しています。
 * 同時操作によるデータの不整合を防ぐため、シングルトンとして実装されています。
 */
public class PrefDataStore {

    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    /**
     * コンストラクタ
     * <p>
     * クラス外部から勝手にインスタンスを作らせないよう、可視性を private に設定しています。
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
        // 同時に複数操作されても不整合が起きないよう、プログラム中で唯一のインスタンス（シングルトン）を生成・利用します
        if (instance == null) {
            // "settings" という名前のPreference DataStoreを構築
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
                    // Preference DataStore は型安全ではないため、保存時と読み出し時の型（ここではString）を明示的に合わせるキーを生成します
                    var prefKey = PreferencesKeys.stringKey(key);
                    return Optional.ofNullable(prefs.get(prefKey));
                })
                .blockingFirst(); // 非同期ストリームから最初の値を同期的に取得
    }

    /**
     * Stringの値をを保存する
     *
     * @param key   保存するキー
     * @param value 保存する値
     */
    public void setString(String key, String value) {
        // 非同期でデータを更新する
        dataStore.updateDataAsync(prefsIn -> {
                    // 書き換え可能なPreferencesに変換する
                    var mutablePreferences = prefsIn.toMutablePreferences();
                    var prefKey = PreferencesKeys.stringKey(key);
                    // 指定したキーに対して値をセット
                    mutablePreferences.set(prefKey, value);
                    return Single.just(mutablePreferences);
                })
                .subscribe(); // 実行のトリガー
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
