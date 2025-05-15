package com.mvohm.quadruple.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mvohm.quadruple.ImmutableQuadruple;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import static com.mvohm.quadruple.immutable.test.AuxMethods.*;

import static org.assertj.core.api.Assertions.*;

/**
 * A quick and dirty set of tests for ImmutableQuadruple class.
 * Mainly, we want to make sure that constructors create instances with expected values,
 * and that arithmetic operations with multiple different operands
 * produce expected results and do not change the original state of the object.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class DraftTests {

  @ParameterizedTest
  @MethodSource(value =  "toConvertFromDouleData")
  @DisplayName("Constructor ImmutableQuadruple(double dValue) creates an instanse with value equal to dValue")
  void testConstructorWithDoubleCreatesCorrectValue(double data)  {
    final double expected = data;
    final double actual = new ImmutableQuadruple(data).doubleValue();

    assertThat(expected).
        withFailMessage("The double value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(actual);
  }

  @ParameterizedTest
  @MethodSource(value =  "toConvertFromLongData")
  @DisplayName("Constructor ImmutableQuadruple(long lValue) creates an instanse with value equal to lValue")
  void testConstructorWithLongCreatesCorrectValue(long data)  {
    final long expected = data;
    final long actual = new ImmutableQuadruple(data).longValue();

    assertThat(expected).
        withFailMessage("The long value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(actual);
  }

  @ParameterizedTest
  @MethodSource(value =  "toConvertFromStringData")
  @DisplayName("Constructor ImmutableQuadruple(String sValue) creates an instanse with value equal to sValue")
  void testConstructorWithStringCreatesCorrectValue(String source, String expected)  {
    final String actual = new ImmutableQuadruple(source).toString();

    final String msg = String.format("The String value '%s' obtained from ImmutableQuadruple\n"
                                      + "  doesn't correspond to expected value '%s'", actual, expected);
    final boolean areOK  = withinPermissibleError(actual, expected);
    if (!areOK) {
      say(msg);
    }
    assertThat(areOK).withFailMessage(msg).isTrue();
  }

  @ParameterizedTest
  @MethodSource(value =  "toConvertFromBigDecimal")
  @DisplayName("Constructor ImmutableQuadruple(BigDecimal bdValue) creates an instanse with value equal to bdValue")
  void testConstructorWithBigDecimalCreatesCorrectValue(BigDecimal source)  {
    final BigDecimal actual = new ImmutableQuadruple(source).bigDecimalValue();

    final String msg = String.format("The BigDecimal value '%s' obtained from ImmutableQuadruple\n"
                                      + "  doesn't correspond to expected value '%s'", actual, source);
    final boolean areOK  = withinPermissibleError(actual, source);
    if (!areOK) {
      say(msg);
    }
    assertThat(areOK).withFailMessage(msg).isTrue();
  }

  @Test
  @DisplayName("Constructor ImmutableQuadruple() creates an instanse with value of 0")
  void testConstructorWithoutParamsCreatesZeroValue()  {
    final BigDecimal actual = new ImmutableQuadruple().bigDecimalValue();

    final String msg = String.format("The value of new ImmutableQuadruple() is not zero");
    final boolean valueIsZero  = (actual.compareTo(BigDecimal.ZERO) == 0);
    if (!valueIsZero) {
      say(msg);
    }
    assertThat(valueIsZero).withFailMessage(msg).isTrue();
  }

  @ParameterizedTest
  @MethodSource(value =  "toCompareImmQuadruples")
  @DisplayName("compareTo(ImmutableQuadruple other) returns correct results")
  void testCompareToImmutableQuadrupleWorksFine(ImmutableQuadruple q1, ImmutableQuadruple q2, int expected)  {
    final int actual = q1.compareTo(q2);
    final String msg = String.format("Comparing %s with %s, expected %s, actual %s",
                                      q1, q2, expected, actual);
    if (expected != actual) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value =  "toCompareQuadruplesWithLongs")
  @DisplayName("compareTo(long other) returns correct results")
  void testCompareToLongWorksFine(ImmutableQuadruple q1, long q2, int expected)  {
    final int actual = q1.compareTo(q2);
    final String msg = String.format("Comparing %s with %s, expected %s, actual %s",
                                      q1, q2, expected, actual);
    if (expected != actual) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value =  "toCompareQuadruplesWithDoubles")
  @DisplayName("compareTo(double other) returns correct results")
  void testCompareToDoubleWorksFine(ImmutableQuadruple q1, double q2, int expected)  {
    final int actual = q1.compareTo(q2);
    final String msg = String.format("Comparing %s with %s, expected %s, actual %s",
                                      q1, q2, expected, actual);
    if (expected != actual) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }


//#######################################################################################
//### Various debugging stuff
//#######################################################################################

  private static void showLSBs() {
    final ImmutableQuadruple q1 = new ImmutableQuadruple(DraftTestData.SOME_NUMBER);         //  17
    final ImmutableQuadruple q2 = new ImmutableQuadruple(new BigDecimal(DraftTestData.SOME_NUMBER). // Non-distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.2e-39), MC_50), MC_50)); //  18
    final ImmutableQuadruple q3 = new ImmutableQuadruple(new BigDecimal(DraftTestData.SOME_NUMBER). // Distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.8e-39), MC_50), MC_50));                    //  19

//    q3 = new ImmutableQuadruple(BigDecimal.ONE.add(new BigDecimal(1.0e-39), MC_50));
//    q2 = ImmutableQuadruple.MIN_VALUE;
//    q3 = ImmutableQuadruple.MAX_VALUE;
    say("q1: %s (%s)", q1, hexStringOf(q1));
    say("q2: %s (%s)", q2, hexStringOf(q2));
    say("q3: %s (%s)", q3, hexStringOf(q3));
  }

  // Positive infinity < NaN ???? Yes! NaN is greater than anything else
  private static void comareImmQuadNaNtoNaN() {
    say("comare ImmQuad NaN to NaN");
    final ImmutableQuadruple d1 = new ImmutableQuadruple(Double.NaN);
    final ImmutableQuadruple d2 = new ImmutableQuadruple(Double.NaN);
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + ImmutableQuadruple.compare(d1, d2));
  }

  private static void comareDoubleNaNtoNaN() {
    say("comare Double NaN to NaN");
    final Double d1 = Double.NaN;
    final Double d2 = Double.NaN;
    say("Equal:   " + (d1.equals(d2)));       // Wrapper types are considered equal
    say("Compare: " + Double.compare(d1, d2));
    say("Primitives:");
    final double d1_ = d1.doubleValue();
    final double d2_ = d2.doubleValue();
    say("Equal:   " + (d1 == d2));            // Primitive types are considered different ...
    say("Compare: " + Double.compare(d1, d2));
  }

  private static void comareImmQuadNanInfinity() {
    say("comare ImmQuad Nan Infinity");
    final ImmutableQuadruple d1 = new ImmutableQuadruple(Double.NaN);
    final ImmutableQuadruple d2 = new ImmutableQuadruple(Double.POSITIVE_INFINITY);
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + ImmutableQuadruple.compare(d1, d2));
  }

  private static void comareDoubleNanInfinity() {
    say("comare Double Nan Infinity");
    final Double d1 = Double.NaN;
    final Double d2 = Double.POSITIVE_INFINITY;
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + Double.compare(d1, d2));
  }

  public static void main(String... args) {
    comareImmQuadNaNtoNaN();
    say();
    comareImmQuadNanInfinity();
    say();
    comareDoubleNaNtoNaN();
    say();
    comareDoubleNanInfinity();
    say();
    showLSBs();
  }

}
