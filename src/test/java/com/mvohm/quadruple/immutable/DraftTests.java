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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.mvohm.quadruple.immutable.test.AuxMethods.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A quick and dirty set of tests for ImmutableQuadruple class.
 * Mainly, we want to make sure that constructors create instances with expected values,
 * and that arithmetic operations with multiple different operands
 * produce expected results and do not change the original state of the object.
 */
@TestInstance(Lifecycle.PER_CLASS)

public class DraftTests {

  private static final int RAND_ARRAY_SIZE = 10_000_000;
  private static final double PERMITTED_DUPLICATES_PER_1M = 200;    // Allow 200 duplicates for 1M hashes of random values
  private static final double PERMITTED_HASH_UNIFORMITY = 0.982;    // for RAND_ARRAY_SIZE == 10_000_000,
  private static final double PERMITTED_RANDOM_UNIFORMITY = 0.969;  // for RAND_ARRAY_SIZE == 10_000_000,
                                                                    // lesser sizes would need lesser thresholds
  private static final int RANDOM_SEQUENCES_TO_TEST = 1_000;        // To test that randoms with equal seeds produce equal sequences

  private static final double MILLION = 1e6;

  // (sqrt(x))^2 may differ from x by 1 in the least significant bit of the mantissa
  private static final double SQR_ERROR_THRESHOLD = 2.94e-39; // 1.47e-39 * 2;
  private static final MathContext MC_80 = new MathContext(80, RoundingMode.HALF_EVEN);


//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromDoubleData")
  @DisplayName("Constructor ImmutableQuadruple(double dValue) creates an instanse with value equal to dValue")
  void testConstructorWithDoubleCreatesCorrectValue(double data)  {
    final double expected = data;
    final double actual = new ImmutableQuadruple(data).doubleValue();

    assertThat(actual).
        withFailMessage("The double value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(expected);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toConvertFromLongData")
  @DisplayName("Constructor ImmutableQuadruple(long lValue) creates an instanse with value equal to lValue")
  void testConstructorWithLongCreatesCorrectValue(long data)  {
    final long expected = data;
    final long actual = new ImmutableQuadruple(data).longValue();

    assertThat(actual).
        withFailMessage("The long value obtained from ImmutableQuadruple differs from its source value").
        isEqualTo(expected);
  }

//  @Disabled
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

//  @Disabled
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

//  @Disabled
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

// Testing public int compareTo(ImmutableQuadruple other) {
//  @Disabled
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

//  @Disabled
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

//  @Disabled
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

//  @Disabled
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

//  @Disabled
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

//  @Disabled
  @Test
  @DisplayName("hashCode() produces reasonable distribution for random values")
  void testHashCodeDistributionForRandomValues()  {
    final int randSeed = new Random().nextInt();
    final List<ImmutableQuadruple> testData = DraftTestData.randomValues(RAND_ARRAY_SIZE, randSeed);
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
    if (uniformity < PERMITTED_HASH_UNIFORMITY) {
      say(msg2);
    }

    assertThat((double)duplicates).withFailMessage(msg1).isLessThanOrEqualTo(permittedDuplicates);
    assertThat(uniformity).withFailMessage(msg2).isGreaterThanOrEqualTo(PERMITTED_HASH_UNIFORMITY);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#adjacentPairs")
  @DisplayName("hashCode() returns different results for values ​​that differ in only one bit ")
  void testHashCodeDiffersForAdjacentValues(ImmutableQuadruple q1, ImmutableQuadruple q2)  {
    // Coincidences of hashes for different values is possible, but the probability of this is very low.
    final int hash1 = q1.hashCode();
    final int hash2 = q2.hashCode();
    final String msg = String.format("hashCode() returned equal results (%s, %s)\nfor different values %s, %s",
                                      hash1, hash2, q1, q2);
    if (hash1 == hash2) {
      say(msg);
    }
    assertThat(hash1).withFailMessage(msg).isNotEqualTo(hash2);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareImmQuadruples")
  @DisplayName("compare(q1, q2) returns correct value")
  void testCompareReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, int expected) {
    final int actual = ImmutableQuadruple.compare(q1, q2);
    final String msg = String.format("compare() returned %s for values %s, %s; expected %s",
                                      actual, q1, q2, expected);
    if (actual != expected) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareMagnitudes")
  @DisplayName("compareMagnitudeTo(q1) returns correct value")
  void testCompareMagnitudeToReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, int expected) {
    final int actual = q1.compareMagnitudeTo(q2);
    final String msg = String.format("comparecompareMagnitudeTo() returned %s for values %s, %s; expected %s",
                                      actual, q1, q2, expected);
    if (actual != expected) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toCompareMagnitudes")
  @DisplayName("compareMagnitudes(q1, q2) returns correct value")
  void testCompareMagnitudesReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, int expected) {
    final int actual = ImmutableQuadruple.compareMagnitudes(q1, q2);
    final String msg = String.format("compareMagnitudes() returned %s for values %s, %s; expected %s",
                                      actual, q1, q2, expected);
    if (actual != expected) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toFindMax")
  @DisplayName("max(q1, q2) returns correct value")
  void testMaxReturnsMax(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    final ImmutableQuadruple actual = ImmutableQuadruple.max(q1, q2);
    final String msg = String.format("max() returned %s for values %s, %s; expected %s",
                                      actual, q1, q2, expected);
    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toFindMin")
  @DisplayName("min(q1, q2) returns correct value")
  void testMinReturnsMin(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    final ImmutableQuadruple actual = ImmutableQuadruple.min(q1, q2);
    final String msg = String.format("min() returned %s for values %s, %s; expected %s",
                                      actual, q1, q2, expected);
    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public ImmutableQuadruple add(ImmutableQuadruple summand) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddImmutableQuadruple")
  @DisplayName("q1.add(q2) returns correct value")
  void testAddImmutableQuadrupleReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.add(q2);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public ImmutableQuadruple  add(long summand) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddLong")
  @DisplayName("q.add(Long l) returns correct value")
  void testAddLongReturnsCorrectResult(ImmutableQuadruple q1, Long summand, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.add(summand);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                    q1, summand, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public ImmutableQuadruple  add(double summand) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddDouble")
  @DisplayName("q.add(Double dl) returns correct value")
  void testAddDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double summand, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.add(summand);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                    q1, summand, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public static ImmutableQuadruple add(ImmutableQuadruple op1, ImmutableQuadruple op2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddImmutableQuadruple")
  @DisplayName("ImmutableQuadruple.add(q1, q2) returns correct value")
  void testAddTwoQuadruoplesReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.add(q1, q2);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public static ImmutableQuadruple add(ImmutableQuadruple op1, long op2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddLong")
  @DisplayName("ImmutableQuadruple.add(q1, Long l) returns correct value")
  void testAddQuadrupleAndLongReturnsCorrectResult(ImmutableQuadruple q1, Long summand, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.add(q1, summand);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                      q1, summand, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, // not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public static ImmutableQuadruple add(ImmutableQuadruple op1, double op2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toAddDouble")
  @DisplayName("ImmutableQuadruple.add(q1, Double dl) returns correct value")
  void testAddQuadrupleAndDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double summand, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.add(summand);
    final String msg = String.format("adding %s and %s resulted in %s; expected %s",
                                    q1, summand, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public ImmutableQuadruple subtract(ImmutableQuadruple subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractImmutableQuadruple")
  @DisplayName("q1.subtract(q2) returns correct value")
  void testSubtractImmutableQuadrupleReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.subtract(q2);
    final String msg = String.format("Subtracting %s from %s resulted in %s; expected %s",
                                    q2, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//  public ImmutableQuadruple subtract(long subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractLong")
  @DisplayName("q.subtract(Long l) returns correct value")
  void testSubtractLongReturnsCorrectResult(ImmutableQuadruple q1, Long subtrahend, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.subtract(subtrahend);
    final String msg = String.format("subtracting %s from %s resulted in %s; expected %s",
                                      subtrahend, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple subtract(double subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractDouble")
  @DisplayName("q.subtract(Double dl) returns correct value")
  void testSubtractDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double subtrahend, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.subtract(subtrahend);
    final String msg = String.format("subtracting %s from %s resulted in %s; expected %s",
                                      subtrahend, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, ImmutableQuadruple subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractImmutableQuadruple")
  @DisplayName("ImmutableQuadruple.subtract(q1, q2) returns correct value")
  void testSubtractTwoQuadruplesReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.subtract(q1, q2);
    final String msg = String.format("Subtracting %s from %s resulted in %s; expected %s",
                                    q2, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, long subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractLong")
  @DisplayName("ImmutableQuadruple.subtract(q1, Long l) returns correct value")
  void testSubtractQuadrupleAndLongReturnsCorrectResult(ImmutableQuadruple q1, Long subtrahend, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.subtract(q1, subtrahend);
    final String msg = String.format("subtracting %s from %s resulted in %s; expected %s",
                                      subtrahend, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, double subtrahend) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toSubtractDouble")
  @DisplayName("ImmutableQuadruple.subtract(q1, Double dl) returns correct value")
  void testSubtractQuadruoleAndDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double subtrahend, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.subtract(q1, subtrahend);
    final String msg = String.format("subtracting %s from %s resulted in %s; expected %s",
                                      subtrahend, q1, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple multiply(ImmutableQuadruple factor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyImmutableQuadruple")
  @DisplayName("q1.multiply(q2) returns correct value")
  void testMultiplyImmutableQuadrupleReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.multiply(q2);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple multiply(long factor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyLong")
  @DisplayName("q.multiply(Long l) returns correct value")
  void testMultiplyLongReturnsCorrectResult(ImmutableQuadruple q1, Long factor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.multiply(factor);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                      q1, factor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple multiply(double factor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyDouble")
  @DisplayName("q.multiply(Double d) returns correct value")
  void testMultiplyDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double factor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.multiply(factor);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                      q1, factor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, ImmutableQuadruple factor2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyImmutableQuadruple")
  @DisplayName("ImmutableQuadruple.multiply(q1, q2) returns correct value")
  void testMultiplyTwoQuadruplesReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.multiply(q1, q2);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, long factor2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyLong")
  @DisplayName("ImmutableQuadruple.multiply(q1, Long l) returns correct value")
  void testMultiplyQuadrupleAndLongReturnsCorrectResult(ImmutableQuadruple q1, Long factor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.multiply(q1, factor);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                      q1, factor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, double factor2) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toMultiplyDouble")
  @DisplayName("ImmutableQuadruple.multiply(q1, Double d) returns correct value")
  void testMultiplyQuadrupleAndDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double factor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.multiply(q1, factor);
    final String msg = String.format("Multiplying %s by %s resulted in %s; expected %s",
                                      q1, factor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple divide(ImmutableQuadruple divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideImmutableQuadruple")
  @DisplayName("q1.divide(q2) returns correct value")
  void testDivideImmutableQuadrupleReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.divide(q2);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple divide(long divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideLong")
  @DisplayName("q.divide(Long l) returns correct value")
  void testDivideLongReturnsCorrectResult(ImmutableQuadruple q1, Long divisor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.divide(divisor);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                      q1, divisor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public ImmutableQuadruple divide(double divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideDouble")
  @DisplayName("q.divide(Double d) returns correct value")
  void testDivideDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double divisor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.divide(divisor);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                      q1, divisor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple divide(ImmutableQuadruple dividend, ImmutableQuadruple divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideImmutableQuadruple")
  @DisplayName("ImmutableQuadruple.divide(q1, q2) returns correct value")
  void testDivideTwoQuadruplesReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple q2, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.divide(q1, q2);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                    q1, q2, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple divide(ImmutableQuadruple dividend, long divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideLong")
  @DisplayName("ImmutableQuadruple.divide(q1, Long l) returns correct value")
  void testDivideQuadrupleAndLongReturnsCorrectResult(ImmutableQuadruple q1, Long divisor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.divide(q1, divisor);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                      q1, divisor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

// public static ImmutableQuadruple divide(ImmutableQuadruple dividend, double divisor) {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toDivideDouble")
  @DisplayName("ImmutableQuadruple.divide(q1, Double d) returns correct value")
  void testDivideQuadrupleAndDoubleReturnsCorrectResult(ImmutableQuadruple q1, Double divisor, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = ImmutableQuadruple.divide(q1, divisor);
    final String msg = String.format("Dividing %s by %s resulted in %s; expected %s",
                                      q1, divisor, actual, expected);

    if (actual.isNaN() && expected.isNaN()) { // NaN is never equal to anything, not even another NaN
      actual = ImmutableQuadruple.ONE;
      expected = ImmutableQuadruple.ONE;
    }

    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//public ImmutableQuadruple sqrt() {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#qOperands")
  @DisplayName("ImmutableQuadruple.sqrt() returns correct value")
  void testSquareRootReturnsCorrectResult(ImmutableQuadruple q1) {
    final ImmutableQuadruple sqrt = q1.sqrt();

    String expectedSqrStr, actualSqrStr;
    actualSqrStr  = sqrt.toString();
    double error = 0;
    if (q1.isInfinite() || q1.isNaN()) {
      expectedSqrStr = q1.toString();
    } else if (q1.isNegative()) {
      expectedSqrStr = ImmutableQuadruple.NaN.toString();
    } else if (q1.isZero()) {
      expectedSqrStr = ImmutableQuadruple.ZERO.toString();
    } else {
      final BigDecimal bdSqrt = sqrt.bigDecimalValue();
      final BigDecimal actualBdSqr = bdSqrt.multiply(bdSqrt, MC_80);
      final BigDecimal expectedBdSqr = q1.bigDecimalValue();
      expectedSqrStr = new ImmutableQuadruple(expectedBdSqr).toString();
      final BigDecimal bdError = expectedBdSqr.subtract(actualBdSqr, MC_80).divide(expectedBdSqr, MC_80);
      error = bdError.doubleValue();
    }

    final String msg = String.format("Actual square of found sqrt, \n%s was \n%s, expected to be equal the original value \n%s, error = %s",
                                      actualSqrStr, expectedSqrStr, q1, error);
    if (Math.abs(error) > SQR_ERROR_THRESHOLD) {
      say(msg);
    }
    assertThat(error).withFailMessage(msg).isLessThan(SQR_ERROR_THRESHOLD);
  }

//public ImmutableQuadruple sqrt() {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#qOperands")
  @DisplayName("ImmutableQuadruple.sqrt(ImmutableQuadruple q) returns correct value")
  void testStaticSquareRootReturnsCorrectResult(ImmutableQuadruple q1) {
    final ImmutableQuadruple sqrt = ImmutableQuadruple.sqrt(q1);

    String expectedSqrStr, actualSqrStr;
    actualSqrStr  = sqrt.toString();
    double error = 0;
    if (q1.isInfinite() || q1.isNaN()) {
      expectedSqrStr = q1.toString();
    } else if (q1.isNegative()) {
      expectedSqrStr = ImmutableQuadruple.NaN.toString();
    } else if (q1.isZero()) {
      expectedSqrStr = ImmutableQuadruple.ZERO.toString();
    } else {
      final BigDecimal bdSqrt = sqrt.bigDecimalValue();
      final BigDecimal actualBdSqr = bdSqrt.multiply(bdSqrt, MC_80);
      final BigDecimal expectedBdSqr = q1.bigDecimalValue();
      expectedSqrStr = new ImmutableQuadruple(expectedBdSqr).toString();
      final BigDecimal bdError = expectedBdSqr.subtract(actualBdSqr, MC_80).divide(expectedBdSqr, MC_80);
      error = bdError.doubleValue();
    }

    final String msg = String.format("Actual square of found sqrt, \n%s was \n%s, expected to be equal the original value \n%s, error = %s",
                                      actualSqrStr, expectedSqrStr, q1, error);
    if (Math.abs(error) > SQR_ERROR_THRESHOLD) {
      say(msg);
    }
    assertThat(error).withFailMessage(msg).isLessThan(SQR_ERROR_THRESHOLD);
  }

//public ImmutableQuadruple negate() {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toTestNegate")
  @DisplayName("q.negate() returns correct value")
  void testNegeteReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.negate();
    final String msg = String.format("Negation of %s gave %s, expected %s", q1, actual, expected);
    if (actual.isNaN() && expected.isNaN()) {
      actual = new ImmutableQuadruple(1);
      expected = new ImmutableQuadruple(1);
    }
    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//public ImmutableQuadruple abs() {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toTestAbs")
  @DisplayName("q.abs() returns correct value")
  void testAbsReturnsCorrectResult(ImmutableQuadruple q1, ImmutableQuadruple expected) {
    ImmutableQuadruple actual = q1.abs();
    final String msg = String.format("abs(%s) gave %s, expected %s", q1, actual, expected);
    if (actual.isNaN() && expected.isNaN()) {
      actual = new ImmutableQuadruple(1);
      expected = new ImmutableQuadruple(1);
    }
    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//public int signum() {
//  @Disabled
  @ParameterizedTest
  @MethodSource(value =  "com.mvohm.quadruple.immutable.DraftTestData#toTestSignum")
  @DisplayName("q.abs() returns correct value")
  void testSignumReturnsCorrectResult(ImmutableQuadruple q1, int expected) {
    final Integer actual = q1.signum();
    final String msg = String.format("signum(%s) gave %s, expected %s", q1, actual, expected);
    if (!actual.equals(expected)) {
      say(msg);
    }
    assertThat(actual).withFailMessage(msg).isEqualTo(expected);
  }

//public static ImmutableQuadruple nextRandom() {
//  @Disabled
  @Test
  @DisplayName("nextRandom returns something at least more or less random")
  void testNextRandomRetunsSomethingRandom() {
    final ImmutableQuadruple[] randoms = new ImmutableQuadruple[RAND_ARRAY_SIZE];
    for (int i = 0; i < RAND_ARRAY_SIZE; i++) {
      randoms[i] = ImmutableQuadruple.nextRandom();
    }
    final double actualUniformity = estimateDistribution(randoms);
    final String msg = String.format("Actual dispersion of random numbers %s, worse than expected %s",
                                actualUniformity, PERMITTED_RANDOM_UNIFORMITY);
    if (actualUniformity <= PERMITTED_RANDOM_UNIFORMITY) {
      say(msg);
    }
    assertThat(actualUniformity).withFailMessage(msg).isGreaterThan(PERMITTED_RANDOM_UNIFORMITY);
  }

//  public static ImmutableQuadruple nextRandom(Random rand) {
// @Disabled
  @Test
  @DisplayName("nextRandom(int randSeed) returns equal sequences for equal seeds")
  void testNextRandomWithSeedRetunsEqualSequencesForEqualSeed() {
    final int[] seeds = new int[RANDOM_SEQUENCES_TO_TEST];
    final ImmutableQuadruple[][][] randomSequences = new ImmutableQuadruple[RANDOM_SEQUENCES_TO_TEST][RANDOM_SEQUENCES_TO_TEST][2];
    final Random random = new Random();

    // Fill the seeds
    for (int i = 0; i < RANDOM_SEQUENCES_TO_TEST; i++) {
      seeds[i] = random.nextInt();
    }

    // 1st set of sequences
    for (int i = 0; i < RANDOM_SEQUENCES_TO_TEST; i++) {
      final Random rand = new Random(seeds[i]);
      for (int j = 0; j < RANDOM_SEQUENCES_TO_TEST; j++) {  // Generate a sequence with the given seed
        randomSequences[i][j][0] = ImmutableQuadruple.nextRandom(rand);
      }
    }

    // 2nd set of sequences
    for (int i = 0; i < RANDOM_SEQUENCES_TO_TEST; i++) {
      final Random rand = new Random(seeds[i]);             // Same seed as for the 1st set
      for (int j = 0; j < RANDOM_SEQUENCES_TO_TEST; j++) {  // Generate a sequence with the given seed
        randomSequences[i][j][1] = ImmutableQuadruple.nextRandom(rand); // Expect it to be equal to seq[i][j][0]
      }
    }

    boolean sequencesAreEqual = true;

  OUTER:
    for (int i = 0; i < RANDOM_SEQUENCES_TO_TEST; i++) {
      for (int j = 0; j < RANDOM_SEQUENCES_TO_TEST; j++) {
        if (!randomSequences[i][j][0].equals(randomSequences[i][j][1])) {
          sequencesAreEqual = false;
          break OUTER;
        }
      }
    }

    final String msg = "Random sequences made of the same seed are different";
    if (!sequencesAreEqual) {
      say(msg);
    }
    assertThat(sequencesAreEqual).withFailMessage(msg).isTrue();
  }

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

  private static double estimateDistribution(ImmutableQuadruple[] values) {
    // Roughly estimate uniformity by putting values into "buckets"
    final int rows = 100, cols = 100;
    final int[][] counts = new int[cols][rows];
    final int length = values.length;
    final int samplesPerColumn = length / cols;
    for (int i = 0; i < values.length; i++) {
      final int col = i / samplesPerColumn;
      final int row = values[i].multiply(rows).intValue();
      counts[col][row]++;
    }

    // Calculate standard relative deviations
    final double expected = (double)length / (cols * rows);
    double variance = 0;
    for (int col = 0; col < cols; col++) {
      for (int row = 0; row < rows; row++) {
        variance += Math.pow((counts[col][row] - expected)/expected, 2);
      }
    }
    variance /= cols * rows;
    final double stddev = Math.sqrt(variance);
    return 1.0 / (1.0 + stddev); // Normalize
  }

}
