package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {
  String cur_state ="0";
  // todo: add fields as needed

  @Override
  public String output() {
    // todo: return output based on the current state
    return cur_state;
  }

  @Override
  public void insertDigit(int digit) {
    if (digit>9 || digit<0)
    {
      throw new  RuntimeException();
    }
    if (cur_state.equals("0"))
    {
      cur_state =Integer.toString(digit);
    }
    else{
      cur_state +=digit;
    }
    // todo: insert a digit
  }

  @Override
  public void insertPlus() {
    cur_state +="+";
    // todo: insert a plus
  }

  @Override
  public void insertMinus() {
    cur_state +="-";
    // todo: insert a minus
  }

  @Override
  public void insertEquals() {
    boolean last_was_num=true;
    boolean last_was_plus = true;
    int last_val=0;
    int cur_val = 0;
    for (int i = 0; i < cur_state.length(); i++) {
      char ch = cur_state.charAt(i);
      if (ch >= '0' && ch <= '9') {
        cur_val *= 10;
        cur_val += (ch - '0');
        last_was_num = true;
      } else if (last_was_num)//last was a num so this is the first order
      {
        if (last_was_plus) {
          last_val += cur_val;
        } else last_val -= cur_val;
        cur_val = 0;
        if (ch == '+') {
          last_was_plus = true;
        } else if (ch == '-') {
          last_was_plus = false;
        }
        last_was_num = false;
      }
    }
      if (last_was_plus)
      {
        last_val+=cur_val;
      }
      else last_val-=cur_val;
      cur_state = Integer.toString(last_val);

      // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
  }

  @Override
  public void deleteLast() {
    if (cur_state.length()==1)
    {
      cur_state="0";
    }
    else
    {
      cur_state = cur_state.substring(0, cur_state.length()-1);
    }
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
  }

  @Override
  public void clear() {
    cur_state = "0";
    // todo: clear everything (same as no-input was never given)
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.s = cur_state;
    // todo: insert all data to the state, so in the future we can load from this state
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    cur_state = casted.s;
    // todo: use the CalculatorState to load
  }

  private static class CalculatorState implements Serializable {
    String s;
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
  }
}
