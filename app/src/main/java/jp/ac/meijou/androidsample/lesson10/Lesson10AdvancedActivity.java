package jp.ac.meijou.androidsample.lesson10;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import jp.ac.meijou.androidsample.databinding.ActivityLesson10AdvancedBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Lesson10 Advanced: パラメータをつけた画像の取得
 */
public class Lesson10AdvancedActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private ActivityLesson10AdvancedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson10AdvancedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lesson10AdvGetButton.setOnClickListener(view -> {
            var text = binding.lesson10AdvEditText.getText().toString();
            // textパラメータをつけたURLの作成
            var url = Uri.parse("https://placehold.jp/500x500.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            // 画像の取得開始
            getImage(url);
        });
    }

    /**
     * placehold.jpから画像を取得し、ImageViewを更新する
     *
     * @param url 画像のURL
     */
    private void getImage(String url) {
        // リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url(url)
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
                runOnUiThread(() -> binding.lesson10AdvImage.setImageBitmap(bitmap));
            }
        });
    }
}