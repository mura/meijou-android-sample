package jp.ac.meijou.androidsample.lesson6;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.databinding.ActivityLesson6Binding;

/**
 * Lesson6: さまざまなUIコンポーネント
 * <p>
 * チェックボックス、ラジオボタン、トグルスイッチ、シークバーなどの
 * 各種UI（View・Widget）のイベント取得方法や、トースト（Toast）の表示方法を学ぶアクティビティです。
 */
public class Lesson6Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson6.xml）と紐付くView Bindingオブジェクト。
     */
    private ActivityLesson6Binding binding;

    /**
     * 現在表示している ImageButton の画像リソースIDを保持する変数。
     */
    @DrawableRes
    private int imageButtonRes = android.R.drawable.btn_star_big_off;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 各種UIコンポーネントのイベントリスナーを設定します。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ImageButtonのクリックイベント：押されるたびに画像のON/OFFを切り替える
        binding.lesson6ImageButton.setOnClickListener(view -> {
            if (imageButtonRes == android.R.drawable.btn_star_big_off) {
                imageButtonRes = android.R.drawable.btn_star_big_on;
            } else {
                imageButtonRes = android.R.drawable.btn_star_big_off;
            }
            binding.lesson6ImageButton.setImageResource(imageButtonRes);
        });

        // CheckBoxの状態変化イベント：チェックの有無（isChecked）をTextViewに表示
        binding.lesson6CheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("check: " + isChecked);
        });

        // RadioGroupの状態変化イベント：選択されたRadioButtonのテキストを取得して表示
        binding.lesson6RadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = group.findViewById(checkedId);
            binding.lesson6Text.setText("radio: " + radioButton.getText());
        });

        // ToggleButtonの状態変化イベント：ON/OFF状態をテキスト表示
        binding.lesson6Toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("toggle: " + isChecked);
        });

        // Switchの状態変化イベント：ON/OFF状態をテキスト表示
        binding.lesson6Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("switch: " + isChecked);
        });

        // SeekBarのシークイベント：ユーザーの操作に合わせてProgressBarの進捗を同期する
        binding.lesson6SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.lesson6ProgressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // FloatingActionButton(FAB)のクリックイベント：短いメッセージ（Toast）をポップアップ表示する
        binding.lesson6Fab.setOnClickListener(view -> {
            Toast.makeText(this, "トーストテスト", Toast.LENGTH_SHORT).show();
        });
    }
}