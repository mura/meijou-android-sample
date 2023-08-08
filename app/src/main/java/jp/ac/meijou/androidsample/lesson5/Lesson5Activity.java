package jp.ac.meijou.androidsample.lesson5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;

import jp.ac.meijou.androidsample.databinding.ActivityLesson5Binding;

/**
 * Lesson5: DataStoreの使い方
 */
public class Lesson5Activity extends AppCompatActivity {

    private static final Preferences.Key<String> NAME = PreferencesKeys.stringKey("name");

    private ActivityLesson5Binding binding;
    private Lesson5DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStore = Lesson5DataStore.getInstance(getApplicationContext());

        dataStore.get(NAME).subscribe(name -> binding.lesson5Text.setText(name));
    }
}