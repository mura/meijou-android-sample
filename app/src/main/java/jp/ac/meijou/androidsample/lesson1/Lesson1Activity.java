package jp.ac.meijou.androidsample.lesson1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.R;

/**
 * Lesson1: findViewByIdの使い方
 */
public class Lesson1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        TextView text = findViewById(R.id.lesson1_text);
        text.setText("Hello Lesson1");
    }
}