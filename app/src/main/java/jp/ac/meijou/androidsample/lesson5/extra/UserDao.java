package jp.ac.meijou.androidsample.lesson5.extra;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * userテーブルを操作するDAO
 */
@Dao
public interface UserDao {

    /**
     * テーブルのレコードを全件返す
     *
     * @return Userのリスト
     */
    @Query("SELECT * FROM user")
    List<User> getAll();

    /**
     * ユーザーを追加する
     *
     * @param users 追加したいUser
     */
    @Insert
    void insertAll(User... users);

    /**
     * ユーザーを削除する
     *
     * @param user 削除したいUser
     */
    @Delete
    void delete(User user);
}
