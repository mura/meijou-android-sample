package jp.ac.meijou.androidsample.lesson1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.databinding.ActivityLesson1Binding;

/**
 * Lesson1: テキストの編集
 * <p>
 * TextViewを用いたテキストの表示や、プログラムからのテキスト変更など、
 * UI要素の基本的な操作方法を学ぶためのアクティビティです。
 */
public class Lesson1Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson1.xml）と紐付き、
     * 画面内のUI要素（View）へのアクセスを簡単にするためのView Bindingオブジェクト。
     */
    private ActivityLesson1Binding binding;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 画面のレイアウト設定や、テキストの初期表示などのセットアップを行います。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // View Binding: findViewById()に変わるViewを操作する記述を簡単にするしくみ
        // レイアウトファイルに対応したクラスが自動で生成される
        // binding = ActivityLesson1Binding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        // ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //     return insets;
        // });

        // Activityに存在している R.id.lesson1_text のIDを持つViewを探す
        TextView text = findViewById(R.id.lesson1_text);
        // TextView.setText() にStringを渡すと表示が即時反映される
        text.setText("Hello Lesson1");
        // TextView.setText() には string.xml に定義したリソースも指定できる
        // text.setText(R.string.name);

        // findViewById()しなくても、クラスのフィールドとしてアクセスできる
        // binding.lesson1Text.setText("Hello Lesson1");
        // binding.lesson1Text.setText(R.string.name);
    }
}