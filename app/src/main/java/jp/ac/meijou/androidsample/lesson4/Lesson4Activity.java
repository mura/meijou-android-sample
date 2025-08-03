package jp.ac.meijou.androidsample.lesson4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.databinding.ActivityLesson4Binding;

/**
 * Lesson4: EditTextの使い方
 */
public class Lesson4Activity extends AppCompatActivity {

    private ActivityLesson4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setOnClickListener();
//        setTextChangedListener();
    }

    /**
     * ボタンを押したらTextViewに値をセットする
     */
    private void setOnClickListener() {
        binding.lesson4Button.setOnClickListener(view -> {
            var text = binding.lesson4EditText.getText();
            binding.lesson4Text.setText(text);
        });
    }

    /**
     * EditTextの文字が変更されるたびにTextViewを変更する
     */
    private void setTextChangedListener() {
        binding.lesson4EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // テキストが更新される直前に呼ばれる
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 文字を1つ入力された時に呼ばれる
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // テキストが更新されたあとに呼ばれる
                binding.lesson4Text.setText(editable.toString());
            }
        });
    }
}