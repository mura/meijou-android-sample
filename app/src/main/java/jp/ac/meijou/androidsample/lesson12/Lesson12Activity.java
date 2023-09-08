package jp.ac.meijou.androidsample.lesson12;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import jp.ac.meijou.androidsample.databinding.ActivityLesson12Binding;

public class Lesson12Activity extends AppCompatActivity {

    private ActivityLesson12Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson12Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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