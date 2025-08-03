package jp.ac.meijou.androidsample.lesson12;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import jp.ac.meijou.androidsample.databinding.ActivityLesson12Binding;

/**
 * Lesson12: CalendarViewの使い方
 */
public class Lesson12Activity extends AppCompatActivity {

    private ActivityLesson12Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson12Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // CalendarViewで日付を選んだときに、TextViewに日付を表示する
            var date = LocalDate.of(year, month, dayOfMonth)
                    .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
            binding.textView.setText(date);
        });

        binding.dateButton.setOnClickListener(view -> {
            // カレンダーの日付を 2023/1/1 にする
            var date = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
                    .toInstant(ZoneOffset.of("+09:00"))
                    .toEpochMilli();
            binding.calendarView.setDate(date);
        });
    }
}