package jp.ac.meijou.androidsample.lesson5.extra;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import jp.ac.meijou.androidsample.databinding.ListItemLesson5ExtraUserBinding;

/**
 * RecyclerViewの表示内容を保持するクラス
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private ListItemLesson5ExtraUserBinding binding;

    /**
     * コンストラクタ
     *
     * @param binding ListItemLesson5ExtraUserBinding
     */
    public UserViewHolder(@NonNull ListItemLesson5ExtraUserBinding binding) {
        this(binding.getRoot());
        this.binding = binding;
    }

    /**
     * コンストラクタ
     *
     * @param itemView ViewHolder管理したいView
     */
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Userの内容をViewに反映する
     *
     * @param user 表示したいUser
     */
    public void onBind(User user) {
        binding.lesson5ExtraName.setText(user.name);
        var createdAt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(user.createdAt), ZoneId.of("Asia/Tokyo"))
                .format(DATE_TIME_FORMAT);
        binding.lesson5ExtraCreatedAt.setText(createdAt);
    }
}
