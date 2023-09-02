package jp.ac.meijou.androidsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jp.ac.meijou.androidsample.databinding.ActivityMainBinding;
import jp.ac.meijou.androidsample.databinding.ListItemBinding;
import jp.ac.meijou.androidsample.lesson1.Lesson1Activity;
import jp.ac.meijou.androidsample.lesson10.Lesson10Activity;
import jp.ac.meijou.androidsample.lesson11.Lesson11Activity;
import jp.ac.meijou.androidsample.lesson2.Lesson2Activity;
import jp.ac.meijou.androidsample.lesson3.Lesson3Activity;
import jp.ac.meijou.androidsample.lesson4.Lesson4Activity;
import jp.ac.meijou.androidsample.lesson5.Lesson5Activity;
import jp.ac.meijou.androidsample.lesson6.Lesson6Activity;
import jp.ac.meijou.androidsample.lesson7.Lesson7Activity;
import jp.ac.meijou.androidsample.lesson7.Lesson7ExtraActivity;
import jp.ac.meijou.androidsample.lesson8.Lesson8FirstActivity;
import jp.ac.meijou.androidsample.lesson9.Lesson9Activity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var list = List.of(
                new ListItem("Lesson1", "findViewByIdの使い方", Lesson1Activity.class),
                new ListItem("Lesson2", "View Bindingの使い方", Lesson2Activity.class),
                new ListItem("Lesson3", "Buttonの使い方", Lesson3Activity.class),
                new ListItem("Lesson4", "EditTextの使い方", Lesson4Activity.class),
                new ListItem("Lesson5", "DataStoreの使い方", Lesson5Activity.class),
                new ListItem("Lesson6", "さまざまなUIコンポーネント", Lesson6Activity.class),
                new ListItem("Lesson7Extra", "ConstraintLayoutの使い方", Lesson7ExtraActivity.class),
                new ListItem("Lesson7", "電卓を作ろう", Lesson7Activity.class),
                new ListItem("Lesson8", "ほかのActivityへの画面遷移", Lesson8FirstActivity.class),
                new ListItem("Lesson9", "ネットワーク通信とJSON", Lesson9Activity.class),
                new ListItem("Lesson10", "ネットワーク通信と画像", Lesson10Activity.class),
                new ListItem("Lesson10", "ネットワーク情報の取得", Lesson11Activity.class)
        );
        binding.recyclerView.setAdapter(new Adapter(list));
        var layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        var itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.recyclerView.addItemDecoration(itemDecoration);
    }

    private static class ListItem {
        public final String title;
        public final String description;
        public final Class<?> activity;

        private ListItem(String title, String description, Class<?> activity) {
            this.title = title;
            this.description = description;
            this.activity = activity;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }

        public void onBind(ListItem value) {
            binding.title.setText(value.title);
            binding.description.setText(value.description);
        }
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private final List<ListItem> list;

        public Adapter(List<ListItem> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            var view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            var viewHolder = new ViewHolder(view);
            view.setOnClickListener(itemView -> {
                var item = list.get(viewHolder.getAdapterPosition());
                var intent = new Intent(itemView.getContext(), item.activity);
                itemView.getContext().startActivity(intent);
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.onBind(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}