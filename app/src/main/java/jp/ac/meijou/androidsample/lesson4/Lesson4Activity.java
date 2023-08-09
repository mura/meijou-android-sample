package jp.ac.meijou.androidsample.lesson4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson4Binding;

/**
 * Lesson4: EditTextの使い方
 */
public class Lesson4Activity extends AppCompatActivity {

    private ActivityLesson4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.lesson4Text.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}