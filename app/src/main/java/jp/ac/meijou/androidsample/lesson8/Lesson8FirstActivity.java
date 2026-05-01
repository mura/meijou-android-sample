package jp.ac.meijou.androidsample.lesson8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.androidsample.databinding.ActivityLesson8FirstBinding;

/**
 * Lesson8: Intent を使った画面遷移（遷移元 / 1画面目）
 * <p>
 * Intent の基礎概念とその実装方法を学ぶアクティビティです。<br>
 * 以下の4種類の Intent / 画面遷移パターンをボタンごとに体験できます：
 * </p>
 * <ol>
 *   <li><b>明示的Intent</b> ― 特定のActivity（Lesson8SecondActivity）を直接指定して起動する</li>
 *   <li><b>暗黙的Intent</b> ― アクション（ACTION_VIEW）とURIを指定し、OSがブラウザを選択して起動する</li>
 *   <li><b>データの送信</b> ― putExtra でEditTextの文字列を2画面目に渡す</li>
 *   <li><b>結果の受け取り</b> ― ActivityResultLauncher を使い、2画面目からの返り値を受け取ってTextViewに表示する</li>
 * </ol>
 * <p>
 * 【スライド対応】Slide 26〜55「画面遷移 / データの受け渡し / ActivityResultLauncher」の
 * 演習1〜3のサンプル実装に相当します。
 * </p>
 */
public class Lesson8FirstActivity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson8_first.xml）と紐付く View Binding オブジェクト。
     */
    private ActivityLesson8FirstBinding binding;

    /**
     * 2画面目（Lesson8SecondActivity）から返り値を受け取るための ActivityResultLauncher。
     * <p>
     * {@code registerForActivityResult} は {@code onCreate()} より前に
     * フィールド初期化として宣言する必要があります（ライフサイクルの制約）。<br>
     * 2画面目の {@code setResult} のステータスに応じて以下のように振る舞います：
     * <ul>
     *   <li>{@code RESULT_OK} ― Intentの "ret" キーの文字列を取得してTextViewに表示</li>
     *   <li>{@code RESULT_CANCELED} ― "Result: Canceled" と表示</li>
     *   <li>その他 ― 結果コードをそのまま表示</li>
     * </ul>
     * </p>
     */
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        // 2画面目が RESULT_OK を返した場合：Intentから "ret" キーの文字列を取得して表示
                        // Optional を使い、データが null の場合も安全に処理する
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.lesson8FirstText.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        // 2画面目が RESULT_CANCELED を返した場合（Cancelボタン押下 or 戻るボタン）
                        binding.lesson8FirstText.setText("Result: Canceled");
                    }
                    default -> {
                        // 上記以外の未知の結果コードが返ってきた場合
                        binding.lesson8FirstText.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 各ボタンに Intent を使った画面遷移・アプリ起動のクリックリスナーを設定します。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson8FirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 【演習1】明示的Intent：起動先Activityのクラスを直接指定して遷移する
        // new Intent(コンテキスト, 起動先Activity.class) の形で Intent オブジェクトを作成する
        binding.lesson8FirstButton1.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            startActivity(intent);
        });

        // 暗黙的Intent（カメラ起動）：INTENT_ACTION_STILL_IMAGE_CAMERA で端末のカメラアプリを起動する
        // アクションを指定するだけでOSが対応アプリを自動的に選択して起動する
        binding.lesson8CameraButton.setOnClickListener(view -> {
            var intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            startActivity(intent);
        });

        // 【演習1】暗黙的Intent：アクションとURIを指定してブラウザを起動する
        // ACTION_VIEW + https:// URL でOSがブラウザアプリを選択して起動する
        // 複数のブラウザが入っている場合はアプリ選択ダイアログが表示されることもある
        binding.lesson8FirstButton2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        // 【演習2】データの送信：putExtra でEditTextの入力文字を2画面目に渡す
        // putExtra("キー名", 値) の形で任意のデータをIntentに添付できる
        // キー名は受け取り側と一致させる必要がある（ここでは "text"）
        binding.lesson8FirstButton3.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            intent.putExtra("text", binding.lesson8FirstEditText.getText().toString());
            startActivity(intent);
        });

        // 【演習3】結果の受け取り：ActivityResultLauncher 経由でIntentを起動する
        // startActivity の代わりに getActivityResult.launch() を使うことで、
        // 2画面目が setResult で返した値を上記コールバックで受け取ることができる
        binding.lesson8FirstButton4.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            getActivityResult.launch(intent);
        });
    }
}