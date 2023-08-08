package jp.ac.meijou.androidsample.lesson3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson3Binding;

/**
 * Lesson3: Buttonの使い方
 */
public class Lesson3Activity extends AppCompatActivity {

    private ActivityLesson3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lesson3Button.setOnClickListener(view ->
                binding.lesson3Text.setText("Hello Lesson3"));
    }
}