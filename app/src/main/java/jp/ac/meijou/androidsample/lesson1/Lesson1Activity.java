package jp.ac.meijou.androidsample.lesson1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.databinding.ActivityLesson1Binding;

/**
 * Lesson1: テキストの編集
 */
public class Lesson1Activity extends AppCompatActivity {

    private ActivityLesson1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
        // binding = ActivityLesson1Binding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());

        TextView text = findViewById(R.id.lesson1_text);
        text.setText("Hello Lesson1");
        // text.setText(R.string.name);

        // binding.lesson1Text.setText("Hello Lesson1");
        // binding.lesson1Text.setText(R.string.name);
    }
}