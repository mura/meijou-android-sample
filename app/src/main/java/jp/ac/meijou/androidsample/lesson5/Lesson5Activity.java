package jp.ac.meijou.androidsample.lesson5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson5Binding;

/**
 * Lesson5: DataStoreの使い方
 */
public class Lesson5Activity extends AppCompatActivity {

    private ActivityLesson5Binding binding;
    private PrefDataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStore = PrefDataStore.getInstance(getApplicationContext());
        dataStore.getString("name")
                .ifPresent(name -> binding.lesson5Text.setText(name));

        binding.lesson5ChangeButton.setOnClickListener(view -> {
            var text = binding.lesson5EditText.getText().toString();
            binding.lesson5Text.setText(text);
        });

        binding.lesson5SaveButton.setOnClickListener(view -> {
            var text = binding.lesson5EditText.getText().toString();
            dataStore.setString("name", text);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        dataStore.getString("name")
//                .ifPresent(name -> binding.lesson5Text.setText(name));
    }
}