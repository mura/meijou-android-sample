package jp.ac.meijou.androidsample.lesson8;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import jp.ac.meijou.androidsample.databinding.ActivityLesson8SecondBinding;

/**
 * Lesson8: ほかのActivityへの画面遷移（遷移先）
 */
public class Lesson8SecondActivity extends AppCompatActivity {

    private ActivityLesson8SecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson8SecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Optional.ofNullable(getIntent().getStringExtra("text"))
                .ifPresent(text -> binding.lesson8SecondText.setText(text));

        binding.lesson8SecondButton1.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.lesson8SecondButton2.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}