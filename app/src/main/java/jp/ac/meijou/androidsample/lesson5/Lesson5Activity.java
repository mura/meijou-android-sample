package jp.ac.meijou.androidsample.lesson5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.databinding.ActivityLesson5Binding;

/**
 * Lesson5: DataStoreの使い方
 * <p>
 * アプリ終了後もデータを保持する「データの永続化」の基本として、
 * Preference DataStore を用いたキー・バリュー形式のデータ保存・読み込み方法を学ぶアクティビティです。
 */
public class Lesson5Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson5.xml）と紐付くView Bindingオブジェクト。
     */
    private ActivityLesson5Binding binding;
    
    /**
     * DataStoreを操作するためのヘルパークラスのインスタンス。
     * （設定値などの保存に適しており、通常シングルトンとして扱います）
     */
    private PrefDataStore dataStore;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // DataStoreのインスタンスを取得（シングルトン）
        dataStore = PrefDataStore.getInstance(getApplicationContext());
        
        // "name" というキーで保存されている文字列を読み込み、存在すればTextViewにセットする
        dataStore.getString("name")
                .ifPresent(name -> binding.lesson5Text.setText(name));

        // Changeボタンが押されたら、EditTextの内容をTextViewに反映する（保存はしない）
        binding.lesson5ChangeButton.setOnClickListener(view -> {
            var text = binding.lesson5EditText.getText().toString();
            binding.lesson5Text.setText(text);
        });

        // Saveボタンが押されたら、EditTextの内容をDataStoreに永続化保存する
        binding.lesson5SaveButton.setOnClickListener(view -> {
            var text = binding.lesson5EditText.getText().toString();
            dataStore.setString("name", text);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        dataStore.getString("name")
//                .ifPresent(name -> binding.lesson5Text.setText(name));
    }
}