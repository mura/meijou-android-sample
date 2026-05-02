package jp.ac.meijou.androidsample.lesson7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.R;

/**
 * Lesson7（追加演習）: ConstraintLayout の練習用サンプル画面。
 * <p>
 * Lesson7 の電卓演習とは別に、ConstraintLayout を使ったレイアウト構成を
 * 単独で確認するための追加アクティビティです。<br>
 * View Binding を使わず、{@code setContentView(R.layout.activity_lesson7_ex)} で
 * レイアウトを直接設定するシンプルな構成になっています。
 * </p>
 * <p>
 * 【スライド対応】Slide 19〜22「ConstraintLayout の練習演習」の参考実装に相当します。
 * </p>
 */
public class Lesson7ExtraActivity extends AppCompatActivity {

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * EdgeToEdge 表示を有効にし、レイアウトを設定します。
     * システムバー（ステータスバー・ナビゲーションバー）の高さ分を
     * padding に反映することでコンテンツが隠れないようにしています。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // View Binding を使わず、XMLレイアウトを直接指定して設定する
        setContentView(R.layout.activity_lesson7_ex);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}