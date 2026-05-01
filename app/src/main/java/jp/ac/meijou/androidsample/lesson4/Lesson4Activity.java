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
 * <p>
 * テキスト入力欄（EditText）から文字列を取得する方法や、
 * 文字入力の変更を検知するイベントリスナー（TextWatcher）の使い方を学ぶアクティビティです。
 */
public class Lesson4Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson4.xml）と紐付き、
     * 画面内のUI要素（View）へのアクセスを簡単にするためのView Bindingオブジェクト。
     */
    private ActivityLesson4Binding binding;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
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
        // setTextChangedListener();
    }

    /**
     * ボタンが押されたときに、EditTextに入力されている文字列を取得し、
     * それをTextViewに反映する処理を設定します。
     */
    private void setOnClickListener() {
        binding.lesson4Button.setOnClickListener(view -> {
            // EditTextのgetText()を呼び出すと、現在入力されている文字列が取得できる
            var text = binding.lesson4EditText.getText();
            binding.lesson4Text.setText(text);
        });
    }

    /**
     * EditTextの文字が変更されるたびに、その変更内容をリアルタイムでTextViewに反映する
     * TextWatcher（テキスト変更監視リスナー）を設定します。
     */
    private void setTextChangedListener() {
        binding.lesson4EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // テキストが更新される直前に呼ばれるコールバック
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 文字が1つ入力された時などに呼ばれるコールバック
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // テキストが更新されたあとに呼ばれるコールバック
                // editableに変更後の文字列が渡されるため、これをTextViewにセットする
                binding.lesson4Text.setText(editable.toString());
            }
        });
    }
}