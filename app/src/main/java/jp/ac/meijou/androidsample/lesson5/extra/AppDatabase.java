package jp.ac.meijou.androidsample.lesson5.extra;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Roomで管理するデータベースの定義
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * UserDaoを返す
     *
     * @return UserDao
     */
    public abstract UserDao userDao();
}
