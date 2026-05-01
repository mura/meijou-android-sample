package jp.ac.meijou.androidsample.lesson3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.databinding.ActivityLesson3Binding;

/**
 * Lesson3: Buttonの使い方
 * <p>
 * ボタンが押されたときのイベント処理（OnClickListener）や、
 * イベントをトリガーにしたUI要素の更新方法を学ぶためのアクティビティです。
 */
public class Lesson3Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson3.xml）と紐付き、
     * 画面内のUI要素（View）へのアクセスを簡単にするためのView Bindingオブジェクト。
     */
    private ActivityLesson3Binding binding;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 画面のレイアウト設定や、ボタンに対するクリックイベントリスナーの登録を行います。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // View Bindingの初期化
        binding = ActivityLesson3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // setOnClickListener()の中に書いた処理が、ボタンがクリックされた時に実行される
        binding.lesson3Button.setOnClickListener(view -> {
            // 引数の view は押されたViewのインスタンス（この場合は lesson3Button）
            binding.lesson3Text.setText("Hello Lesson3");
        });
    }
}