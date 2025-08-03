package jp.ac.meijou.androidsample.lesson5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        EdgeToEdge.enable(this);
        binding = ActivityLesson5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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