package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }

///can also create delte on single digit
  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.deleteLast();
    String expected = "1";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
    // todo: implement test
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
  }


  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!
  @Test
  public void when_callingDeleteOnLastSingle_then_lastOutputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.deleteLast();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_UsingTwoEquals_then_shouldWork(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    String expected = "14";
    assertEquals(expected, calculatorUnderTest.output());
  }
  @Test
  public void when_DeleteNone_then_shouldGet0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_DeleteInTheMidOfNum_then_getCorrectNum(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(6);
    String expected = "86";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_DoubleClear_then_getCorrectNum(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);

    String expected = "2-1";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_insertingZeroesAtStart_then_numShouldWork(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(1);
    String expected = "1";
    assertEquals(expected, calculatorUnderTest.output());
  }
  @Test
  public void when_insertingZeroesAtStartAndPlus_then_computeRight(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertEquals();
    String expected = "35";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_complicatedEq_then_computeRight(){
    //  - given input "8-7=+4=-1=", expected output is "4"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    String expected = "4";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_2Cals_then_loadsCorrectly() {
    SimpleCalculatorImpl calculatorUnderTest1 = new SimpleCalculatorImpl();
    SimpleCalculatorImpl calculatorUnderTest2 = new SimpleCalculatorImpl();
    calculatorUnderTest1.insertDigit(7);
    calculatorUnderTest1.insertDigit(7);
    calculatorUnderTest2.insertDigit(2);
    calculatorUnderTest2.loadState(calculatorUnderTest1.saveState());
    String expected = "77";
    assertEquals(expected, calculatorUnderTest2.output());
  }
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well

  @Test
  public void when_loadString_then_doesntDoAnythin() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.loadState("56");
    String expected = "4";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_4action1Eq_then_computeRight(){
    //  - given input "8-7=+4=-1=", expected output is "4"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    String expected = "4";
    assertEquals(expected, calculatorUnderTest.output());
  }
}