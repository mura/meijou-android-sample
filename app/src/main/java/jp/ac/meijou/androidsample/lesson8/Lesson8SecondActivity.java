package jp.ac.meijou.androidsample.lesson8;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.R;

/**
 * Lesson8: ほかのActivityへの画面遷移（遷移先）
 */
public class Lesson8SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson8_second);
    }
}