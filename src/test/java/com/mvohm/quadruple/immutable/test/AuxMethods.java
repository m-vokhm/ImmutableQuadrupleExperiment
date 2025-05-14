/*

 Copyright 2021 M.Vokhmentsev

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

*/

package com.mvohm.quadruple.immutable.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.DoubleStream;

import com.mvohm.quadruple.Quadruple;

/**
 * A set of convenience static methods to be used by other classes of the quadruple.test package.
 * Includes mainly a number of methods to convert values of various types to each other,
 * and to output values to console.
 *
 * @author M.Vokhmentev
 *
 */

@SuppressWarnings("serial")
class NotImplementedException extends RuntimeException {
  public NotImplementedException(String message) { super(message); }
}

public class AuxMethods {

  /** The bits of the mantissa of a {@code double} represented as a {@code long} */
  public static final long DOUBLE_MANT_MASK     = 0x000f_ffff_ffff_ffffL;

  /** The bits of the exponent of a {@code double} represented as a {@code long} */
  public static final long DOUBLE_EXP_MASK      = 0x7ff0_0000_0000_0000L;

  /** The sign bit of of a {@code double} represented as a {@code long} */
  public static final long DOUBLE_SIGN_MASK     = 0x8000_0000_0000_0000L;

  /** Double's exponent bias, that is the exponent of a {@code double} values falling within the range of 1.0d ... 1.999d (values with unbiased exponent = 0) */
  public static final int DOUBLE_EXP_BIAS       = 0x0000_03FF;

  /** The length of the integer part in an exponential form of a number, including the decimal point e.g. '-1.' for '-1.1234e-35' */
  private static final int INTEGER_PART_LENGTH  = 3;
  /** The length of the exponent part in an exponential form of a number, including 'e', e.g. 'e-35'. for '-1.1234e-35' */
  private static final int EXPONENT_PART_LENGTH = 4;

  private static final int DEFAULT_PRECISION = 50;

  private final static MathContext MC_50 = new MathContext(50, RoundingMode.HALF_EVEN);
  /** Half of the least significant bit */
  private final static BigDecimal HALF_OF_LSB = new BigDecimal("1.4693679385278593849609206715278070972733319459651e-39");


  public static boolean withinPermissibleError(String actual, String expected) {
    if (actual.equals(expected)) {
      return true;
    }
    if (   (actual.equals("NEGATIVE_INFINITY") || actual.equals("-Infinity"))
        && (expected.equals("NEGATIVE_INFINITY") || expected.equals("-Infinity"))) {
      return true;
    }
    if (   (actual.equals("POSITIVE_INFINITY") || actual.equals("Infinity"))
        && (expected.equals("POSITIVE_INFINITY") || expected.equals("Infinity"))) {
      return true;
    }

    final BigDecimal bdActual   = new BigDecimal(actual);
    final BigDecimal bdExpected = new BigDecimal(expected);
    BigDecimal error = bdActual.subtract(bdExpected, MC_50);
    if (!bdExpected.equals(BigDecimal.ZERO)) {
      error = error.divide(bdExpected, MC_50).abs();
    }
    return error.compareTo(HALF_OF_LSB) <= 0;
  }

  public static boolean withinPermissibleError(BigDecimal bdActual, BigDecimal bdExpected) {
    BigDecimal error = bdActual.subtract(bdExpected, MC_50);
    if (!bdExpected.equals(BigDecimal.ZERO)) {
      error = error.divide(bdExpected, MC_50).abs();
    }
    return error.compareTo(HALF_OF_LSB) <= 0;
  }



 /****************************************************************************************
  *** Output to the console **************************************************************
  ****************************************************************************************/

  /** == System.out.println(); */
	public static void say() 	{ System.out.println(); }

	/** == System.out.println(Object o);
	 * @param o {@code Object} to print */
	public static void say(Object o) 	{ System.out.println(o); }

	/** == System.out.print(Object o);
   * @param o {@code Object} to print */
	public static void say_(Object o) 	{ System.out.print(o); }

	/** == System.out.println(String.format(String format, Object... args)
	 * @param format a format string to format the {@code args}
	 * @param args arguments to format
	 * @see String#format(String, Object...)
	 */
	public static void say(String format, Object... args) { System.out.println(String.format(format, args)); }

  /** == System.out.print(String.format(String format, Object... args)
   * @param format a format string to format the {@code args}
   * @param args arguments to format
   * @see String#format(String, Object...)
   */
	public static void say_(String format, Object... args) { System.out.print(String.format(format, args)); }

	/** Terminates execution with exit code == 0 */
	public static void exit() { System.exit(0); }

 /****************************************************************************************
  *** Printing matrices and arrays *******************************************************
  ****************************************************************************************/

 /****************************************************************************************
  *** Conversions to Hexadecimal string representations **********************************
  ****************************************************************************************/

  /**
   * Returns a hexadecimal string representation of the given int value,
   * with digits grouped by 4, e.g. "60bf_b765".
   * @param intValue the value to convert to hexadecimal string
   * @return the hexadecimal string representation of the given int value
   */
  public static String hexStr(int intValue) {
    return String.format(
        "%04x_%04x",
        intValue >> 16 & 0xFFFF, intValue & 0xFFFF);
  } // public static String hexStr(int iValue) {

	/**
	 * Returns a hexadecimal string representation of the given long value,
	 * with digits grouped by 4, e.g. "60bf_b765_972a_f2a2".
	 * @param longValue the value to convert to hexadecimal string
	 * @return the hexadecimal string representation of the given long value
	 */
  public static String hexStr(long longValue) {
    return String.format(
        "%04x_%04x_%04x_%04x",
        longValue >> 48 & 0xFFFF, longValue >> 32 & 0xFFFF,
        longValue >> 16 & 0xFFFF, longValue & 0xFFFF);
  } // public static String hexStr(long lValue) {

  /**
   * Returns a hexadecimal string representation of the given double value
   * showing its sign, mantissa, and exponent separately, e.g.
   * <span style="white-space:nowrap">"-6_a09e_667f_3bcd e 3ff"</span>
   * @param doubleValue the value to convert to hexadecimal string
   * @return the hexadecimal string representation of the given double value
   */
  public static  String hexStr(double doubleValue) {
    final long l = Double.doubleToLongBits(doubleValue);
    String expStr = hexStr((l & DOUBLE_EXP_MASK) >> 52);
    expStr = expStr.substring(expStr.length() - 3, expStr.length());
    String mantStr = hexStr(l);
    mantStr = mantStr.substring(mantStr.length() - 16, mantStr.length());
    final String signStr = (l & DOUBLE_SIGN_MASK) == 0? "+" : "-";
    return String.format("%s%s e %s", signStr, mantStr, expStr);
  } // public static  String hexStr(double dValue) {

  /* ******************************************************************
   * Handling computational errors
   ********************************************************************/

  public static class ErrorSet {
    private final double mse, meanError, maxError;
    private long time;

    ErrorSet(double mse, double meanError, double maxError) {
      this.mse = mse; this.meanError = meanError; this.maxError = maxError;
    }

    public ErrorSet setTime(long time) {
      this.time = time;
      return this;
    }

    public double mse()       { return mse; }
    public double meanError() { return meanError; }
    public double maxError()  { return maxError; }

    public long getTime()     { return time; }
  }

  /* **************************************************
   *** findErrors() for double arrays *****************
   ****************************************************/

  public static ErrorSet findErrors(double[] expectedSolution, double[] actualSolution) {
    return findErrors(expectedSolution, actualSolution, false);
  }

  public static ErrorSet findErrors(double[] expectedSolution, double[] actualSolution, double range) {
    return findErrors(expectedSolution, actualSolution, range, false);
  }

  public static ErrorSet findErrors(double[] expectedSolution, double[] actualSolution, boolean printErrors) {
    final double range = findRange(expectedSolution);
    return findErrors(expectedSolution, actualSolution, range, printErrors);
  }

  public static ErrorSet findErrors(double[] expectedSolution, double[] actualSolution, double range, boolean printErrors) {
    final double[] diff = subtractVectors(expectedSolution, actualSolution);
    return findErrors(diff, range, printErrors);
  }




  /** Given the difference between expected and actual arrays and the range of expected values,
   *  finds the errors of one-dimentional arrays compared to expected ones */
  private static ErrorSet findErrors(double[] diff, final double range, boolean printErrors) {
    diff = DoubleStream.of(diff).map(v -> v/range).toArray();
    final double mse = Math.sqrt(DoubleStream.of(diff).map(v -> v * v).average().getAsDouble());
    final double meanError = DoubleStream.of(diff).average().getAsDouble();
    final double maxError = DoubleStream.of(diff).map(Math::abs).max().getAsDouble();
    if (printErrors) {
      say("  mean err: %9.3e, max err: %9.3e, mse: %9.3e\n", meanError, maxError, mse);
    }
    return new ErrorSet(mse, meanError, maxError);
  }

  /** Given the difference between expected and actual arrays and the range of expected values,
   *  finds the errors of two-dimentional arrays compared to expected ones */
  private static ErrorSet findErrors(double[][] diff, final double range, boolean printErrors) {
    final double[] diff1d = Arrays.stream(diff).flatMapToDouble(Arrays::stream).toArray();
    return findErrors(diff1d, range, printErrors);
  }

  private static double findRange(double[] expectedSolution) {
    final double maxValue = Math.max(DoubleStream.of(expectedSolution).max().getAsDouble(), 0);
    final double minValue = Math.min(DoubleStream.of(expectedSolution).min().getAsDouble(), 0);
    return maxValue - minValue;
  }

  private static double findRange(double[][] expectedSolution) {
    double maxValue = 0, minValue = 0;
    for (int i = 0; i < expectedSolution.length; i++) {
      maxValue = Math.max(DoubleStream.of(expectedSolution[i]).max().getAsDouble(), maxValue);
      minValue = Math.min(DoubleStream.of(expectedSolution[i]).min().getAsDouble(), minValue);
    }
    return maxValue - minValue;
  }

  public static void printMethodName() {
    String s = new Exception().getStackTrace()[1].toString();
    s = s.replaceFirst("\\(.*\\)", "():").replaceFirst(".*\\.", "");
    say(s);
  }

  static double safelyDivide(double divisor, double dividend, double resultForDivisionByZero) {
    return (dividend == 0)? resultForDivisionByZero : divisor / dividend;
  }

  public static double[] subtractVectors(double[] minuend, double[] subtrahend) {
    final int length = minuend.length;
    final double[] result = new double[length];
    for (int i = 0; i < length; i++) {
      result[i] = minuend[i] - subtrahend[i];
    }
    return result;
  }

  public static Quadruple[] subtractVectors(Quadruple[] minuend, Quadruple[] subtrahend) {
    final int length = minuend.length;
    final Quadruple[] result = new Quadruple[length];
    for (int i = 0; i < length; i++) {
      result[i] = Quadruple.subtract(minuend[i], subtrahend[i]);
    }
    return result;
  }

  public static BigDecimal[] subtractVectors(BigDecimal[] minuend, BigDecimal[] subtrahend) {
    final int length = minuend.length;
    final BigDecimal[] result = new BigDecimal[length];
    for (int i = 0; i < length; i++) {
      result[i] = minuend[i].subtract(subtrahend[i]);
    }
    return result;
  }


}
