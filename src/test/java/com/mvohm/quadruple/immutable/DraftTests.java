package com.mvohm.quadruple.immutable;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mvohm.quadruple.ImmutableQuadruple;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

  // // @Disabledd
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromDoubleData")
  @DisplayName("Constructor ImmutableQuadruple(double dValue) creates an instanse with value equal to dValue")
  void testConstructorWithDoubleCreatesCorrectValue(double data)  {
    final double expected = data;
    final double actual = new ImmutableQuadruple(data).doubleValue();

    assertThat(expected).
        withFailMessage("The double value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(actual);
  }

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromLongData")
  @DisplayName("Constructor ImmutableQuadruple(long lValue) creates an instanse with value equal to lValue")
  void testConstructorWithLongCreatesCorrectValue(long data)  {
    final long expected = data;
    final long actual = new ImmutableQuadruple(data).longValue();

    assertThat(expected).
        withFailMessage("The long value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(actual);
  }

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromStringData")
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

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromBigDecimal")
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

  // @Disabled
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

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareImmQuadruples")
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

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareQuadruplesWithLongs")
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

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareQuadruplesWithDoubles")
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

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toTestEquality")
  @DisplayName("equals(Object obj) returns correct results")
  void testEquals(ImmutableQuadruple q1, Object q2, boolean expected)  {
    final boolean actual = q1.equals(q2);
    final String msg = String.format("Comparing %s with %s, expected %s, actual %s",
                                      q1, q2, expected, actual);
    if (expected != actual) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#qOperands")
  @DisplayName("hashCode() returns equal results for equal values")
  void testHashCodeEqualityForEqualValues(ImmutableQuadruple q1)  {
    final ImmutableQuadruple q2 = new ImmutableQuadruple(q1.toString());
    final int hash1 = q1.hashCode();
    final int hash2 = q2.hashCode();
    final String msg = String.format("hashCode() returned different results (%s, %s)\nfor equal values %s, %s",
                                      hash1, hash2, q1, q2);
    if (hash1 != hash2) {
      say(msg);
    }
    assertThat(hash1).withFailMessage(msg).isEqualTo(hash2);
  }

  static private final double PERMITTED_DUPLICATES_PER_1M = 200;
  static private final double PERMITTED_UNIFORMITY = 0.85; // RAND_ARRAY_SIZE should not be less than 2_000
  private static final int RAND_ARRAY_SIZE = 1_000_000;
  private static final int RAND_SEED = 12345;
  private static final double MILLION = 1e6;

  // @Disabled
  @Test
  @DisplayName("hashCode() produces reasonable distribution for random values")
  void testHashCodeDistributionForRandomValues()  {
    final List<ImmutableQuadruple> testData = DraftTestData.randomValues(RAND_ARRAY_SIZE, RAND_SEED);
    final int[] hashCodes = new int[testData.size()];
    for (int i = 0; i < testData.size(); i++) {
      hashCodes[i] = testData.get(i).hashCode();
    }

    // the integer part is the number of duplicates,
    // the fractional part is a measure of distribution uniformity
    final double estimate = estimateDistribution(hashCodes);

    final int duplicates = (int) estimate;
    final double uniformity = estimate - duplicates;
    final double permittedDuplicates = PERMITTED_DUPLICATES_PER_1M
                                      * Math.pow(RAND_ARRAY_SIZE / MILLION, 2);

    final String msg1 = String.format("hashCode() produces too much duplicates (%s) "
                                      + "per 1M of random values", duplicates);
    final String msg2 = String.format("hashCode() produces bad distribution (%.3f) "
                                      + "for random values", uniformity);

    if (duplicates > permittedDuplicates) {
      say(msg1);
    }
    if (uniformity < PERMITTED_UNIFORMITY) {
      say(msg2);
    }

    assertThat((double)duplicates).withFailMessage(msg1).isLessThanOrEqualTo(permittedDuplicates);
    assertThat(uniformity).withFailMessage(msg2).isGreaterThanOrEqualTo(PERMITTED_UNIFORMITY);
  }

  // @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#adjacentPairs")
  @DisplayName("hashCode() returns different results for values ​​that differ in only one bit ")
  void testHashCodeEqualityForEqualValues(ImmutableQuadruple q1, ImmutableQuadruple q2)  {
    // Coincidences of hashes for different values is possible, but the probability of this is very low.
    final int hash1 = q1.hashCode();
    final int hash2 = q2.hashCode();
    final String msg = String.format("hashCode() returned equal results (%s, %s)\nfor different values %s, %s",
                                      hash1, hash2, q1, q2);
    if (hash1 == hash2) {
      say(msg);
    }
    assertThat(hash1).withFailMessage(msg).isNotEqualTo(hash2).isEqualTo(hash2);
  }

// Things to test in the next approach
//  public static int compare(ImmutableQuadruple q1, ImmutableQuadruple q2) {
//  public int compareMagnitudeTo(ImmutableQuadruple other) {
//  public static int compareMagnitudes(ImmutableQuadruple q1, ImmutableQuadruple q2) {
//  public static ImmutableQuadruple max(ImmutableQuadruple q1, ImmutableQuadruple q2) {
//  public static ImmutableQuadruple min(ImmutableQuadruple q1, ImmutableQuadruple q2) {



//#######################################################################################
//### Private helper methods
//#######################################################################################

  /** Returns the number of duplicates in the integer part
   * and a measure of the distribution uniformity in the fractional part
   * @param values -- the data to estimate
   * @return the number of duplicates and a measure of distribution uniformity
   */
  private static double estimateDistribution(int[] values) {
    final int size = values.length;

    // Count duplicates, find min and max
    final Set<Integer> distinct = new HashSet<>();
    int duplicates = 0;
    int minValue = Integer.MAX_VALUE;
    int maxValue = Integer.MIN_VALUE;
    for (final int value : values) {
      if (!distinct.add(value)) {
        duplicates++;
      }
      minValue = Math.min(value, minValue);
      maxValue = Math.max(value, maxValue);
    }
    if (minValue == maxValue) return duplicates == 0? duplicates : 0;

    // Roughly estimate uniformity by putting values into "buckets"
    final int bucketCount = (int)Math.sqrt(size);
    final int[] buckets = new int[bucketCount];
    final long range = (long) maxValue - minValue + 1;
    for (final int value : values) {
      final int index = (int) (((long)value - minValue) * bucketCount / range);
      buckets[index]++;
    }

    // Calculate standard relative deviations
    final double expected = (double)size / bucketCount;
    double variance = 0;
    for (final int count : buckets) {
      variance += Math.pow((count - expected)/expected, 2);
    }
    variance /= bucketCount;
    final double stddev = Math.sqrt(variance);
    double uniformity = 1.0 / (1.0 + stddev); // Normalize
    if (uniformity == 1.0) {
      uniformity = 1 - 1e-15;       // so as not to confuse with duplicates
    }
    return duplicates + uniformity;
  }

//#######################################################################################
//### Various debugging stuff (to be moved to another class, if needed at all)
//#######################################################################################

  private static void showLSBs() {
    // make sure that the first two numbers are indistinguishable;
    // and the third one differs from them by the least significant bit of the mantissa
    final ImmutableQuadruple q1 = new ImmutableQuadruple(DraftTestData.SOME_NUMBER);         //  17
    final ImmutableQuadruple q2 = new ImmutableQuadruple(new BigDecimal(DraftTestData.SOME_NUMBER). // Non-distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.2e-39), MC_50), MC_50));   //  less than the unit of LSB
    final ImmutableQuadruple q3 = new ImmutableQuadruple(new BigDecimal(DraftTestData.SOME_NUMBER). // Distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.8e-39), MC_50), MC_50));   // greater than the unit of LSB

//    q1 = new ImmutableQuadruple(BigDecimal.ONE.add(new BigDecimal(1.5e-39), MC_50));
//    q2 = ImmutableQuadruple.MIN_VALUE;
//    q3 = ImmutableQuadruple.MAX_VALUE;
    say("q1: %52s (%s)", q1, hexStringOf(q1));
    say("q2: %52s (%s)", q2, hexStringOf(q2));
    say("q3: %52s (%s)", q3, hexStringOf(q3));
  }

  private static void compareImmQuadNaNtoNaN() {
    say("Expect two indistinguisahable and one distinct");
    say("compare ImmQuad NaN to NaN");
    final ImmutableQuadruple d1 = new ImmutableQuadruple(Double.NaN);
    final ImmutableQuadruple d2 = new ImmutableQuadruple(Double.NaN);
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + ImmutableQuadruple.compare(d1, d2));
  }

  private static void compareDoubleNaNtoNaN() {
    say("compare Double NaN to NaN");
    final Double d1 = Double.NaN;
    final Double d2 = Double.NaN;
    say("Equal:   " + (d1.equals(d2)));       // Wrapper types are considered equal
    say("Compare: " + Double.compare(d1, d2));
    say("Primitives:");
    final double d1_ = d1.doubleValue();
    final double d2_ = d2.doubleValue();
    say("Equal:   " + (d1_ == d2_));            // Primitive types are considered different ...
    say("Compare: " + Double.compare(d1, d2));
  }

  // Positive infinity < NaN ???? Yes! NaN is greater than anything else
  private static void compareImmQuadNanInfinity() {
    say("compare ImmQuad Nan Infinity");
    final ImmutableQuadruple d1 = new ImmutableQuadruple(Double.NaN);
    final ImmutableQuadruple d2 = new ImmutableQuadruple(Double.POSITIVE_INFINITY);
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + ImmutableQuadruple.compare(d1, d2));
  }

  private static void compareDoubleNanInfinity() {
    say("compare Double Nan to Infinity");
    final Double d1 = Double.NaN;
    final Double d2 = Double.POSITIVE_INFINITY;
    say("Equal:   " + (d1.equals(d2)));
    say("Compare: " + Double.compare(d1, d2));
  }

  public static void main(String... args) {
    compareImmQuadNaNtoNaN();
    say();
    compareImmQuadNanInfinity();
    say();
    compareDoubleNaNtoNaN();
    say();
    compareDoubleNanInfinity();
    say();
    showLSBs();
  }

}
