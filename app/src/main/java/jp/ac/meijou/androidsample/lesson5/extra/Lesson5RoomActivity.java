package jp.ac.meijou.androidsample.lesson5.extra;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import jp.ac.meijou.androidsample.databinding.ActivityLesson5RoomBinding;

/**
 * Lesson5 Extra: RoomとRecyclerViewの使い方
 */
public class Lesson5RoomActivity extends AppCompatActivity {

    /**
     * View Binding
     */
    private ActivityLesson5RoomBinding binding;

    /**
     * DBの操作をするサブスレッド
     */
    private HandlerThread handlerThread;

    /**
     * サブスレッドに処理を送るHandler
     */
    private Handler asyncHandler;

    /**
     * userテーブルを操作するDAO
     */
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson5RoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Room(データベース)はメインスレッドで操作できないのでサブスレッドを作成する
        handlerThread = new HandlerThread("db-thread", Process.THREAD_PRIORITY_DEFAULT);
        handlerThread.start();
        asyncHandler = new Handler(handlerThread.getLooper());

        // データベース(UserDao)のインスタンスを取得
        var db = Databases.getInstance(this);
        userDao = db.userDao();

        // ListAdapterの準備
        var userDiffCallback = new UserDiffCallback();
        var userListAdapter = new UserListAdapter(userDiffCallback);

        asyncHandler.post(() -> {
            // 非同期で全ユーザーリストを取得
            var userList = userDao.getAll();
            runOnUiThread(() -> userListAdapter.submitList(userList));
        });

        // RecyclerViewの設定
        binding.lesson5ExtraRecyclerview.setAdapter(userListAdapter);
        var layoutManager = new LinearLayoutManager(this);
        binding.lesson5ExtraRecyclerview.setLayoutManager(layoutManager);
        var itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.lesson5ExtraRecyclerview.addItemDecoration(itemDecoration);

        binding.lesson5ExtraAdd.setOnClickListener(view -> {
            // Addボタンが押されたとき
            var name = binding.lesson5ExtraEditText.getText().toString();
            asyncHandler.post(() -> {
                // 非同期でデータベースにユーザーを追加
                var user = new User();
                user.name = name;
                user.createdAt = System.currentTimeMillis();
                userDao.insertAll(user);
                userListAdapter.submitList(userDao.getAll());
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Activity破棄時にはサブスレッドを止める
        handlerThread.quit();
    }
}