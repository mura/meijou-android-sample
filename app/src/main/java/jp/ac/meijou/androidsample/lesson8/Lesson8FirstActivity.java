package jp.ac.meijou.androidsample.lesson8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import jp.ac.meijou.androidsample.databinding.ActivityLesson8FirstBinding;

/**
 * Lesson8: ほかのActivityへの画面遷移（遷移元）
 */
public class Lesson8FirstActivity extends AppCompatActivity {

    private ActivityLesson8FirstBinding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.lesson8FirstText.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.lesson8FirstText.setText("Result: Canceled");
                        break;
                    default:
                        binding.lesson8FirstText.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson8FirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 明示的Intent
        binding.lesson8FirstButton1.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            startActivity(intent);
        });

        // 暗黙的Intent
        binding.lesson8FirstButton2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        // 文字を送信
        binding.lesson8FirstButton3.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            intent.putExtra("text", binding.lesson8FirstEditText.getText().toString());
            startActivity(intent);
        });

        // 結果を取得
        binding.lesson8FirstButton4.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            getActivityResult.launch(intent);
        });
    }
}