package jp.ac.meijou.androidsample.lesson9;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.androidsample.databinding.ActivityLesson9Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Lesson9: ネットワーク通信とJSON
 */
public class Lesson9Activity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityLesson9Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // リクエスト先にgistを指定
        var request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                // エラーになる場合は以下のURLを使う
                //.url("https://mura.github.io/meijou-android-sample/gist.json")
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // レスポンスボディをGist型に変換
                var gist = gistJsonAdapter.fromJson(response.body().source());
                // 中身の取り出し
                Optional.ofNullable(gist)
                        .map(g -> g.files.get("OkHttp.txt"))
                        .ifPresent(gistFile -> {
                            // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                            runOnUiThread(() -> binding.lesson9Text.setText(gistFile.content));
                        });
            }
        });
    }
}