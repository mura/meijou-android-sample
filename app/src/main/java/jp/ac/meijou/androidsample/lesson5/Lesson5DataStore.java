package jp.ac.meijou.androidsample.lesson5;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Optional;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

/**
 * Lesson5: SharedPreferenceにアクセスするDataStore
 */
public class Lesson5DataStore {

    private static Lesson5DataStore instance;

    private final RxDataStore<Preferences> dataStore;

    private Lesson5DataStore(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    public static Lesson5DataStore getInstance(Context context) {
        if (instance == null) {
            var dataStore = new RxPreferenceDataStoreBuilder(context, "settings").build();
            instance = new Lesson5DataStore(dataStore);
        }
        return instance;
    }

    public <T> Maybe<T> get(Preferences.Key<T> key) {
        return dataStore.data()
                .mapOptional(prefs -> Optional.ofNullable(prefs.get(key)))
                .singleElement();
    }

    public <T> void set(Preferences.Key<T> key, T value) {
        dataStore.updateDataAsync(prefsIn -> {
                    var mutablePreferences = prefsIn.toMutablePreferences();
                    mutablePreferences.set(key, value);
                    return Single.just(mutablePreferences);
                })
                .subscribe();
    }
}
