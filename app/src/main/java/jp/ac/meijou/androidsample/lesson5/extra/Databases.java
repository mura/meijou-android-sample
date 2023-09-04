package jp.ac.meijou.androidsample.lesson5.extra;

import android.content.Context;

import androidx.room.Room;

/**
 * Databaseのインスタンスを管理するクラス
 */
public class Databases {
    private static AppDatabase db;

    /**
     * データベースのインスタンスを返す
     *
     * @param context Context
     * @return AppDatabase
     */
    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "sample-db")
                    .build();
        }
        return db;
    }
}
