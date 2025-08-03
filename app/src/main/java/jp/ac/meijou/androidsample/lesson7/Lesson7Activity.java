package jp.ac.meijou.androidsample.lesson7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.function.BiFunction;

import jp.ac.meijou.androidsample.databinding.ActivityLesson7Binding;

/**
 * Lesson7: 電卓を作ろう
 */
public class Lesson7Activity extends AppCompatActivity {

    private ActivityLesson7Binding binding;

    private int display;
    private int operand1;
    private int operand2;
    private Operator operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.lesson7Button0.setOnClickListener(view -> pushOperand(0));
        binding.lesson7Button1.setOnClickListener(view -> pushOperand(1));
        binding.lesson7Button2.setOnClickListener(view -> pushOperand(2));
        binding.lesson7Button3.setOnClickListener(view -> pushOperand(3));
        binding.lesson7Button4.setOnClickListener(view -> pushOperand(4));
        binding.lesson7Button5.setOnClickListener(view -> pushOperand(5));
        binding.lesson7Button6.setOnClickListener(view -> pushOperand(6));
        binding.lesson7Button7.setOnClickListener(view -> pushOperand(7));
        binding.lesson7Button8.setOnClickListener(view -> pushOperand(8));
        binding.lesson7Button9.setOnClickListener(view -> pushOperand(9));
        binding.lesson7ButtonAllClear.setOnClickListener(view -> clear());

        binding.lesson7ButtonPlus.setOnClickListener(view -> operator = Operator.PLUS);
        binding.lesson7ButtonMinus.setOnClickListener(view -> operator = Operator.MINUS);
        binding.lesson7ButtonMultiply.setOnClickListener(view -> operator = Operator.MULTIPLY);
        binding.lesson7ButtonDivide.setOnClickListener(view -> operator = Operator.DIVIDE);

        binding.lesson7ButtonEqual.setOnClickListener(view -> calc());
    }

    /**
     * オペランドの末尾にnumを追加する
     *
     * @param num 追加する数字
     */
    private void pushOperand(int num) {
        if (operator == null) {
            operand1 = operand1 * 10 + num;
            display = operand1;
        } else {
            operand2 = operand2 * 10 + num;
            display = operand2;
        }
        binding.lesson7Result.setText(String.valueOf(display));
    }

    /**
     * 計算内容をクリアする
     */
    private void clear() {
        operand1 = 0;
        operand2 = 0;
        display = 0;
        operator = null;
        binding.lesson7Result.setText(String.valueOf(display));
    }

    /**
     * 計算する
     */
    private void calc() {
        if (operator == null) {
            return;
        }
        display = operator.calc.apply(operand1, operand2);
        binding.lesson7Result.setText(String.valueOf(display));
    }

    /**
     * 演算子
     */
    private enum Operator {
        PLUS(Integer::sum),
        MINUS((a, b) -> a - b),
        MULTIPLY((a, b) -> a * b),
        DIVIDE((a, b) -> a / b);

        public final BiFunction<Integer, Integer, Integer> calc;

        Operator(BiFunction<Integer, Integer, Integer> calc) {
            this.calc = calc;
        }
    }
}