package jp.ac.meijou.androidsample.lesson2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson2Binding;

/**
 * Lesson2: View Bindingの使い方
 */
public class Lesson2Activity extends AppCompatActivity {

    private ActivityLesson2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lesson2Text.setText("Hello Lesson2");
    }
}