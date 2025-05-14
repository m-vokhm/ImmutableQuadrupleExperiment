package com.mvohm.quadruple.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

  public static double[] toConvertFromDouleData() {
    return new double[] {
      Double.NEGATIVE_INFINITY,
      Double.POSITIVE_INFINITY,
      Double.MIN_VALUE,
      Double.MAX_VALUE,
      // Double.NaN, // NaN never equals to another NaN
      1.234567890123456789,
      3.33333333333333333333333,
      -1.234567890123456789,
      -3.33333333333333333333333,
      1.234567890123456789e37,
      3.33333333333333333333333e37,
      -1.234567890123456789e37,
      -3.33333333333333333333333e37,
      1.234567890123456789e-37,
      3.33333333333333333333333e-37,
      -1.234567890123456789e-37,
      -3.33333333333333333333333e-37,
    };
  }

  public static long[] toConvertFromLongData() {
    return new long[] {
      Long.MAX_VALUE,
      Long.MIN_VALUE,
      0,
      1234567890,
    };
  }

  public static String[][] toConvertFromStringData() {
    return new String[][] {
        {"NEGATIVE_INFINITY",              "-Infinity"},
        {"POSITIVE_INFINITY",              "Infinity"},
        {"MIN_VALUE",                      "6.672829482607474308148353774991346115977e-646457032"},
        {"MAX_VALUE",                      "1.761613051683963353207493149791840285665e+646456993"},
        {"NaN",                           "NaN"},
        {"1.234567890123456789",           "1.234567890123456789000000000000000000000e+00"},
        {"3.33333333333333333333333",      "3.333333333333333333333330000000000000000e+00"},
        {"-1.234567890123456789",         "-1.234567890123456789000000000000000000000e+00"},
        {"-3.33333333333333333333333",    "-3.333333333333333333333330000000000000000e+00"},
        {"1.234567890123456789e37",        "1.234567890123456789000000000000000000000e+37"},
        {"3.33333333333333333333333e37",   "3.333333333333333333333330000000000000000e+37"},
        {"-1.234567890123456789e37",      "-1.234567890123456789000000000000000000000e+37"},
        {"-3.33333333333333333333333e37", "-3.333333333333333333333330000000000000000e+37"},
        {"1.234567890123456789e-37",       "1.234567890123456789000000000000000000000e-37"},
        {"3.33333333333333333333333e-37",  "3.333333333333333333333330000000000000000e-37"},
        {"-1.234567890123456789e-37",     "-1.234567890123456789000000000000000000000e-37"},
        {"-3.33333333333333333333333e-37","-3.333333333333333333333330000000000000000e-37"},
    };
  }

  private static BigDecimal[] toConvertFromBigDecimal() {
    return new BigDecimal[] {
      new BigDecimal( "6.672829482607474308148353774991346115977e-646457032"),
      new BigDecimal( "1.761613051683963353207493149791840285665e+646456993"),
      new BigDecimal( "1.234567890123456789000000000000000000000e+00"),
      new BigDecimal( "3.333333333333333333333330000000000000000e+00"),
      new BigDecimal("-1.234567890123456789000000000000000000000e+00"),
      new BigDecimal("-3.333333333333333333333330000000000000000e+00"),
      new BigDecimal( "1.234567890123456789000000000000000000000e+37"),
      new BigDecimal( "3.333333333333333333333330000000000000000e+37"),
      new BigDecimal("-1.234567890123456789000000000000000000000e+37"),
      new BigDecimal("-3.333333333333333333333330000000000000000e+37"),
      new BigDecimal( "1.234567890123456789000000000000000000000e-37"),
      new BigDecimal( "3.333333333333333333333330000000000000000e-37"),
      new BigDecimal("-1.234567890123456789000000000000000000000e-37"),
      new BigDecimal("-3.333333333333333333333330000000000000000e-37"),
      new BigDecimal("1.4693679385278593849609206715278070972733319459651e-39"),
    };
  }

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


}
