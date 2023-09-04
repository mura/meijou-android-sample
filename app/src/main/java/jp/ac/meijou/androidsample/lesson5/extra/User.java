package jp.ac.meijou.androidsample.lesson5.extra;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * userテーブルのエンティティ
 */
@Entity
public class User {

    /**
     * プライマリキー(自動生成)
     */
    @PrimaryKey(autoGenerate = true)
    public int uid;

    /**
     * 名前
     */
    @ColumnInfo(name = "name")
    public String name;

    /**
     * 作成日時(UnixTimeのミリ秒)
     */
    @ColumnInfo(name = "created_at")
    public long createdAt;
}
