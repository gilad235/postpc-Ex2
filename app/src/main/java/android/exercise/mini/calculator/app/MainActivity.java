package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;


  private void digitclicker(View view, int digit, TextView maintext)
  {
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        calculator.insertDigit(digit);
        maintext.setText(calculator.output());
      }

    });
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }
    TextView equal = findViewById(R.id.buttonEquals);
    TextView plus = findViewById(R.id.buttonPlus);
    TextView minus = findViewById(R.id.buttonMinus);
    TextView clear = findViewById(R.id.buttonClear);
    View delete = findViewById(R.id.buttonBackSpace);
    TextView digit0 = findViewById(R.id.button0);
    TextView digit1 = findViewById(R.id.button1);
    TextView digit2 = findViewById(R.id.button2);
    TextView digit3 = findViewById(R.id.button3);
    TextView digit4 = findViewById(R.id.button4);
    TextView digit5 = findViewById(R.id.button5);
    TextView digit6 = findViewById(R.id.button6);
    TextView digit7 = findViewById(R.id.button7);
    TextView digit8 = findViewById(R.id.button8);
    TextView digit9 = findViewById(R.id.button9);
    TextView maintext = findViewById(R.id.textViewCalculatorOutput);

    maintext.setText(calculator.output());


    digitclicker(digit0,0,maintext);
    digitclicker(digit1,1,maintext);
    digitclicker(digit2,2,maintext);
    digitclicker(digit3,3,maintext);
    digitclicker(digit4,4,maintext);
    digitclicker(digit5,5,maintext);
    digitclicker(digit6,6,maintext);
    digitclicker(digit7,7,maintext);
    digitclicker(digit8,8,maintext);
    digitclicker(digit9,9,maintext);

    minus.setOnClickListener(v ->
    {
      calculator.insertMinus();
      maintext.setText(calculator.output());
    });
    plus.setOnClickListener(v ->
    {
      calculator.insertPlus();
      maintext.setText(calculator.output());
    });

    equal.setOnClickListener(v ->
    {
      calculator.insertEquals();
      maintext.setText(calculator.output());
    });
    clear.setOnClickListener(v ->
    {
      calculator.clear();
      maintext.setText(calculator.output());
    });
    delete.setOnClickListener(v ->
    {
      calculator.deleteLast();
      maintext.setText(calculator.output());
    });
    /*
    TODO:

    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("cur_state",calculator.saveState());
    // todo: save calculator state into the bundle
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

//    TextView maintext = findViewById(R.id.textViewCalculatorOutput);
    Serializable s = savedInstanceState.getSerializable("cur_state");
    calculator.loadState(s);
//    maintext.setText(s);


//    calculator.loadState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
  }
}