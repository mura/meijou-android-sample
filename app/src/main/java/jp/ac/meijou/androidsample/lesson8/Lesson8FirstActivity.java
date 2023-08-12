package jp.ac.meijou.androidsample.lesson8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson8FirstBinding;

/**
 * Lesson8: ほかのActivityへの画面遷移（遷移元）
 */
public class Lesson8FirstActivity extends AppCompatActivity {

    private ActivityLesson8FirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson8FirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 明示的Intent
        binding.lesson8Button1.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            startActivity(intent);
        });

        // 暗黙的Intent
        binding.lesson8Button2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });
    }
}