package jp.ac.meijou.androidsample.lesson5.extra;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

/**
 * RecyclerViewに表示するデータの差分を計算するCallback
 */
public class UserDiffCallback extends DiffUtil.ItemCallback<User> {


    /**
     * 新旧のアイテムが同一かを判断するメソッド
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return 同一ならtrueを返す
     */
    @Override
    public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.uid == newItem.uid;
    }

    /**
     * 新旧のアイテムの中身が同一かを判断するメソッド
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return 内容が同一ならtrue
     */
    @Override
    public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return Objects.equals(oldItem.name, newItem.name)
                && Objects.equals(oldItem.createdAt, newItem.createdAt);
    }
}
