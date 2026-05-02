package jp.ac.meijou.androidsample.lesson2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.databinding.ActivityLesson2Binding;

/**
 * Lesson2: 画像の表示
 * <p>
 * drawableリソースやVector Assetを利用したImageViewへの画像表示など、
 * Androidアプリにおける基本的な画像（ビットマップ・ベクター）の扱い方を学ぶためのアクティビティです。
 */
public class Lesson2Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson2.xml）と紐付き、
     * 画面内のUI要素（View）へのアクセスを簡単にするためのView Bindingオブジェクト。
     */
    private ActivityLesson2Binding binding;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 画面のレイアウト設定や、UI要素の初期化などのセットアップを行います。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // View Bindingの初期化: レイアウトからクラスのインスタンスを生成する
        binding = ActivityLesson2Binding.inflate(getLayoutInflater());
        // 生成したViewをActivityの画面としてセットする
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bindingオブジェクトを介して、レイアウト内の lesson2Text にテキストを設定する
        binding.lesson2Text.setText("Hello Lesson2");

        // 【演習】画像をコードから変更する場合は、以下のように binding.imageView.setImageResource() などを利用します
        // binding.imageView.setImageResource(R.drawable.ic_launcher_foreground);
    }
}