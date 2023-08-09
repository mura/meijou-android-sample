package jp.ac.meijou.androidsample.lesson6;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.androidsample.databinding.ActivityLesson6Binding;

/**
 * Lesson6: さまざまなUIコンポーネント
 */
public class Lesson6Activity extends AppCompatActivity {

    private ActivityLesson6Binding binding;

    @DrawableRes
    private int imageButtonRes = android.R.drawable.btn_star_big_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lesson6ImageButton.setOnClickListener(view -> {
            if (imageButtonRes == android.R.drawable.btn_star_big_off) {
                imageButtonRes = android.R.drawable.btn_star_big_on;
            } else {
                imageButtonRes = android.R.drawable.btn_star_big_off;
            }
            binding.lesson6ImageButton.setImageResource(imageButtonRes);
        });

        binding.lesson6CheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("check: " + isChecked);
        });

        binding.lesson6RadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = group.findViewById(checkedId);
            binding.lesson6Text.setText("radio: " + radioButton.getText());
        });

        binding.lesson6Toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("toggle: " + isChecked);
        });

        binding.lesson6Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.lesson6Text.setText("switch: " + isChecked);
        });

        binding.lesson6SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.lesson6ProgressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.lesson6Fab.setOnClickListener(view -> {
            Toast.makeText(this, "トーストテスト", Toast.LENGTH_SHORT).show();
        });
    }
}