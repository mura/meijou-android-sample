package jp.ac.meijou.androidsample.lesson5.extra;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import jp.ac.meijou.androidsample.databinding.ListItemLesson5ExtraUserBinding;

/**
 * RecyclerViewの表示内容を制御するAdapter
 */
public class UserListAdapter extends ListAdapter<User, UserViewHolder> {

    /**
     * コンストラクタ
     *
     * @param diffCallback DiffUtil.ItemCallback
     */
    protected UserListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    /**
     * viewのTypeごとにUserViewHolderを作成しないといけないときに呼ばれる
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var inflater = LayoutInflater.from(parent.getContext());
        var binding = ListItemLesson5ExtraUserBinding.inflate(inflater, parent, false);
        return new UserViewHolder(binding);
    }

    /**
     * RecyclerViewがスクロールして、positionの行を表示されるときに呼ばれる
     * リストのpositionのデータをViewHolderに反映する
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        var user = getCurrentList().get(position);
        holder.onBind(user);
    }
}
