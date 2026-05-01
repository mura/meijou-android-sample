package jp.ac.meijou.androidsample.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import jp.ac.meijou.androidsample.databinding.ActivityMainBinding;
import jp.ac.meijou.androidsample.lesson1.Lesson1Activity;
import jp.ac.meijou.androidsample.lesson10.Lesson10Activity;
import jp.ac.meijou.androidsample.lesson10.Lesson10AdvancedActivity;
import jp.ac.meijou.androidsample.lesson11.Lesson11Activity;
import jp.ac.meijou.androidsample.lesson12.Lesson12Activity;
import jp.ac.meijou.androidsample.lesson2.Lesson2Activity;
import jp.ac.meijou.androidsample.lesson3.Lesson3Activity;
import jp.ac.meijou.androidsample.lesson4.Lesson4Activity;
import jp.ac.meijou.androidsample.lesson5.Lesson5Activity;
import jp.ac.meijou.androidsample.lesson5.extra.Lesson5RoomActivity;
import jp.ac.meijou.androidsample.lesson6.Lesson6Activity;
import jp.ac.meijou.androidsample.lesson7.Lesson7Activity;
import jp.ac.meijou.androidsample.lesson7.Lesson7ExtraActivity;
import jp.ac.meijou.androidsample.lesson8.Lesson8FirstActivity;
import jp.ac.meijou.androidsample.lesson9.Lesson9Activity;

/**
 * アプリの起動時に表示されるメイン画面のActivityです。
 * 各レッスンの画面へ遷移するためのリストを表示します。
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 表示するレッスンのリストデータを作成
        var list = List.of(
                new ListItem("Lesson1", "テキストの編集", Lesson1Activity.class),
                new ListItem("Lesson2", "画像の表示", Lesson2Activity.class),
                new ListItem("Lesson3", "Buttonの使い方", Lesson3Activity.class),
                new ListItem("Lesson4", "EditTextの使い方", Lesson4Activity.class),
                new ListItem("Lesson5", "DataStoreの使い方", Lesson5Activity.class),
                new ListItem("Lesson5Extra", "RoomとRecyclerViewの使い方", Lesson5RoomActivity.class),
                new ListItem("Lesson6", "さまざまなUIコンポーネント", Lesson6Activity.class),
                new ListItem("Lesson7Extra", "ConstraintLayoutの使い方", Lesson7ExtraActivity.class),
                new ListItem("Lesson7", "電卓を作ろう", Lesson7Activity.class),
                new ListItem("Lesson8", "ほかのActivityへの画面遷移", Lesson8FirstActivity.class),
                new ListItem("Lesson9", "ネットワーク通信とJSON", Lesson9Activity.class),
                new ListItem("Lesson10", "ネットワーク通信と画像", Lesson10Activity.class),
                new ListItem("Lesson10Advanced", "パラメータをつけた画像の取得", Lesson10AdvancedActivity.class),
                new ListItem("Lesson11", "ネットワーク情報の取得", Lesson11Activity.class),
                new ListItem("Lesson12", "CalendarViewの使い方", Lesson12Activity.class)
        );

        // RecyclerViewにアダプターを設定
        binding.recyclerView.setAdapter(new MainAdapter(list));

        // レイアウトマネージャーを設定
        var layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        // リスト項目間に区切り線を引くためのItemDecorationを追加
        var itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.recyclerView.addItemDecoration(itemDecoration);
    }
}
