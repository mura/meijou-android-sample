package jp.ac.meijou.androidsample.lesson10;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import jp.ac.meijou.androidsample.databinding.ActivityLesson10Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Lesson10: ネットワーク通信と画像
 */
public class Lesson10Activity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private ActivityLesson10Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson10Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url("https://placehold.jp/350x350.png")
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.lesson10Image.setImageBitmap(bitmap));
            }
        });
    }
}