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
 * <p>
 * ConstraintLayout を使って電卓のレイアウトを構築し、実際に四則演算が動作する
 * 電卓アプリを実装するアクティビティです。<br>
 * 数字ボタン（0〜9）、演算子ボタン（+、−、×、÷）、AC（全クリア）、＝（計算実行）
 * の各ボタンに対してクリックイベントを設定し、TextView に計算結果を表示します。
 * </p>
 * <p>
 * 【スライド対応】Slide 24「電卓のレイアウトを作ろう」の演習サンプルに相当します。
 * </p>
 */
public class Lesson7Activity extends AppCompatActivity {

    /**
     * レイアウトファイル（activity_lesson7.xml）と紐付く View Binding オブジェクト。
     */
    private ActivityLesson7Binding binding;

    /**
     * 現在ディスプレイに表示している数値。
     * 入力中の数字、または計算結果を保持します。
     */
    private int display;

    /**
     * 演算の左辺オペランド（1つ目の入力値）。
     * 演算子が選択される前に入力された数値を保持します。
     */
    private int operand1;

    /**
     * 演算の右辺オペランド（2つ目の入力値）。
     * 演算子が選択された後に入力された数値を保持します。
     */
    private int operand2;

    /**
     * 現在選択されている演算子。
     * {@code null} の場合は演算子がまだ選択されていないことを示します。
     */
    private Operator operator;

    /**
     * アクティビティが最初に作成されるときに呼び出されるメソッド。
     * 数字ボタン・演算子ボタン・AC・＝ ボタンそれぞれにクリックリスナーを設定します。
     *
     * @param savedInstanceState 以前に保存された状態データ（存在する場合のみ非null）
     */
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

        // 数字ボタン（0〜9）：押された数字を現在のオペランドの末尾に追加する
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

        // AC（All Clear）ボタン：入力値・演算子をすべてリセットしてディスプレイを0に戻す
        binding.lesson7ButtonAllClear.setOnClickListener(view -> clear());

        // 演算子ボタン：選択した演算子を保持する（＝ボタン押下時に使用）
        binding.lesson7ButtonPlus.setOnClickListener(view -> operator = Operator.PLUS);
        binding.lesson7ButtonMinus.setOnClickListener(view -> operator = Operator.MINUS);
        binding.lesson7ButtonMultiply.setOnClickListener(view -> operator = Operator.MULTIPLY);
        binding.lesson7ButtonDivide.setOnClickListener(view -> operator = Operator.DIVIDE);

        // ＝ボタン：operand1 と operand2 を選択中の演算子で計算し、結果を表示する
        binding.lesson7ButtonEqual.setOnClickListener(view -> calc());
    }

    /**
     * オペランドの末尾にnumを追加する。
     * <p>
     * 演算子が未選択の場合は {@code operand1}、選択済みの場合は {@code operand2} に
     * 数字を追記し、TextView に表示を更新します。
     * </p>
     *
     * @param num 追加する数字（0〜9）
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
     * 計算内容をクリアする。
     * <p>
     * operand1、operand2、display をすべて 0 にリセットし、
     * operator を {@code null} に戻してディスプレイ表示も更新します。
     * </p>
     */
    private void clear() {
        operand1 = 0;
        operand2 = 0;
        display = 0;
        operator = null;
        binding.lesson7Result.setText(String.valueOf(display));
    }

    /**
     * 現在の operand1、operand2 と選択中の演算子で計算を実行し、結果を表示する。
     * <p>
     * 演算子が未選択（{@code null}）の場合は何もしません。
     * </p>
     */
    private void calc() {
        if (operator == null) {
            return;
        }
        display = operator.calc.apply(operand1, operand2);
        binding.lesson7Result.setText(String.valueOf(display));
    }

    /**
     * 電卓の演算子を表す列挙型。
     * <p>
     * 各定数は対応する計算処理（{@link BiFunction}）を保持しており、
     * {@link #calc} フィールドを通じて実際の計算に利用されます。
     * </p>
     */
    private enum Operator {
        // 加算（operand1 + operand2）
        PLUS(Integer::sum),
        // 減算（operand1 − operand2）
        MINUS((a, b) -> a - b),
        // 乗算（operand1 × operand2）
        MULTIPLY((a, b) -> a * b),
        // 除算（operand1 ÷ operand2）※ゼロ除算は未ガード
        DIVIDE((a, b) -> a / b);

        // この演算子に対応する計算処理。
        public final BiFunction<Integer, Integer, Integer> calc;

        Operator(BiFunction<Integer, Integer, Integer> calc) {
            this.calc = calc;
        }
    }
}