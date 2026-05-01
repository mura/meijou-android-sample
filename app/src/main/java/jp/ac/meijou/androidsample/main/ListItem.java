package jp.ac.meijou.androidsample.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * リストに表示する各レッスンの情報を保持するデータクラスです。
 *
 * @param title       レッスンのタイトル（例: "Lesson1"）
 * @param description レッスンの概要・説明
 * @param activity    遷移先のActivityクラス
 */
public record ListItem(String title, String description, Class<? extends Activity> activity) {

    /**
     * 遷移先Activityを起動します。
     *
     * @param context コンテキスト
     */
    public void startActivity(Context context) {
        context.startActivity(new Intent(context, activity));
    }
}
