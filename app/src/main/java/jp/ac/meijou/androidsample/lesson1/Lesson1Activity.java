package jp.ac.meijou.androidsample.lesson1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.androidsample.R;
import jp.ac.meijou.androidsample.databinding.ActivityLesson1Binding;

/**
 * Lesson1: テキストの編集
 */
public class Lesson1Activity extends AppCompatActivity {

    private ActivityLesson1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // binding = ActivityLesson1Binding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        // ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //     return insets;
        // });

        TextView text = findViewById(R.id.lesson1_text);
        text.setText("Hello Lesson1");
        // text.setText(R.string.name);

        // binding.lesson1Text.setText("Hello Lesson1");
        // binding.lesson1Text.setText(R.string.name);
    }
}