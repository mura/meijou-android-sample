package jp.ac.meijou.androidsample.lesson8;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.androidsample.databinding.ActivityLesson8SecondBinding;

/**
 * Lesson8: Intent を使った画面遷移（遷移先 / 2画面目）
 * <p>
 * Lesson8FirstActivity から起動される2画面目のアクティビティです。<br>
 * 以下の機能を担います：
 * </p>
 * <ul>
 *   <li>1画面目から {@code putExtra("text", ...)} で渡された文字列を受け取り、TextViewに表示する</li>
 *   <li>OKボタン押下時は {@code setResult(RESULT_OK, intent)} で文字列を1画面目に返す</li>
 *   <li>Cancelボタン押下時は {@code setResult(RESULT_CANCELED)} でキャンセルを1画面目に通知する</li>
 *   <li>{@code finish()} を呼び出してActivityを自律的に破棄し、1画面目に戻る</li>
 * </ul>
 * <p>
 * 【スライド対応】Slide 40〜55「データの受け渡し / ActivityResultLauncher」の
 * 演習2・演習3のサンプル実装に相当します。
 * </p>
 */
public class Lesson8SecondActivity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson8_second.xml）と紐付く View Binding オブジェクト。
     */
    private ActivityLesson8SecondBinding binding;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * <p>
     * 1画面目から渡されたデータを受け取り、TextViewに表示します。
     * また、OKボタン・Cancelボタンにクリックリスナーを設定します。
     * </p>
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson8SecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 【演習2】データの受け取り：1画面目が putExtra("text", ...) で添付した文字列を取得する
        // getIntent() で起動元Intentを取得し、getStringExtra("text") でキーを指定して値を取り出す
        // Optional を使って null の場合は setText を呼ばないように安全に処理する
        Optional.ofNullable(getIntent().getStringExtra("text"))
                .ifPresent(text -> binding.lesson8SecondText.setText(text));

        // 【演習3】OKボタン：RESULT_OK とデータ（"ret" キーに "OK" という文字列）を1画面目に返す
        // finish() を呼び出してこの Activity を破棄し、1画面目に戻る
        binding.lesson8SecondButton1.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        // 【演習3】Cancelボタン：RESULT_CANCELED を1画面目に返し、Activity を破棄して戻る
        // データを渡す必要がない場合は Intent なしで setResult を呼べばよい
        binding.lesson8SecondButton2.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}