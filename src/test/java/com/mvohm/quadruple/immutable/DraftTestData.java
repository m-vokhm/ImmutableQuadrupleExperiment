package com.mvohm.quadruple.immutable;

import static com.mvohm.quadruple.immutable.test.AuxMethods.MC_50;
import static com.mvohm.quadruple.immutable.test.AuxMethods.MC_120;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import com.mvohm.quadruple.ImmutableQuadruple;

/** Test data sets and generators to be used by DrafTests */
public class DraftTestData {

//#######################################################################################
//### Test data
//#######################################################################################



  static double[] toConvertFromDoubleData() {
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

  static long[] toConvertFromLongData() {
    return new long[] {
      Long.MAX_VALUE,
      Long.MIN_VALUE,
      0,
      1234567890,
    };
  }

  static String[][] toConvertFromStringData() {
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

  static BigDecimal[] toConvertFromBigDecimal() {
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
      new BigDecimal( "1.4693679385278593849609206715278070972733319459651e-39"),
    };
  }

  static final String SOME_NUMBER = "1.234567890123456789012345678901234567876e-21";

  private static ImmutableQuadruple[] qOperands = new ImmutableQuadruple[] {
    ImmutableQuadruple.MAX_VALUE,
    ImmutableQuadruple.NEGATIVE_INFINITY,
    ImmutableQuadruple.POSITIVE_INFINITY,
    ImmutableQuadruple.NaN,
    ImmutableQuadruple.MIN_VALUE,
    ImmutableQuadruple.MIN_NORMAL,
    new ImmutableQuadruple( "1.234567890123456789000000000000000000000e+00"),
    new ImmutableQuadruple( "3.333333333333333333333330000000000000000e+00"),
    new ImmutableQuadruple( "1234.567890123456789000000000000000000000e+00"),
    new ImmutableQuadruple( "3333.333333333333333333330000000000000000e+00"),
    new ImmutableQuadruple( "12345678.90123456789000000000000000000000e+00"),
    new ImmutableQuadruple( "12345678"),
    new ImmutableQuadruple( "-12345678"),
    new ImmutableQuadruple( "33333333.33333333333333330000000000000000e+00"),
    new ImmutableQuadruple("-1.234567890123456789000000000000000000000e+00"),
    new ImmutableQuadruple("-3.333333333333333333333330000000000000000e+00"),
    new ImmutableQuadruple("-1234.567890123456789000000000000000000000e+00"),
    new ImmutableQuadruple("-3333.333333333333333333330000000000000000e+00"),
    new ImmutableQuadruple("-12345678.90123456789000000000000000000000e+00"),
    new ImmutableQuadruple("-33333333.33333333333333330000000000000000e+00"),
    new ImmutableQuadruple( "0"),
    new ImmutableQuadruple(" 1.234567890123456789000000000000000000000e+37"),
    new ImmutableQuadruple( "3.333333333333333333333330000000000000000e+37"),
    new ImmutableQuadruple("-1.234567890123456789000000000000000000000e+37"),
    new ImmutableQuadruple("-3.333333333333333333333330000000000000000e+37"),
    new ImmutableQuadruple(" 1.234567890123456789000000000000000000000e-37"),
    new ImmutableQuadruple( "3.333333333333333333333330000000000000000e-37"),
    new ImmutableQuadruple("-1.234567890123456789000000000000000000000e-37"),
    new ImmutableQuadruple("-3.333333333333333333333330000000000000000e-37"),

    new ImmutableQuadruple(SOME_NUMBER),
    new ImmutableQuadruple(new BigDecimal(SOME_NUMBER). // Non-distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.2e-39), MC_50), MC_50)),
    new ImmutableQuadruple(new BigDecimal(SOME_NUMBER). // Distinguishable from the previous
                            multiply(BigDecimal.ONE.add(new BigDecimal(1.8e-39), MC_50), MC_50)),

  };

  private static long[] lOperands = new long[] {
    Long.MIN_VALUE,
    -12345679, // < -12345678.9
    -12345678, // > -12345678.9
    -33333334,
    -33333333,
    -1234,
    0,
    1234,
    12345678, // < -12345678.9
    12345679, // > -12345678.9
    33333333,
    33333334,
    Long.MAX_VALUE,
  };

  private static double[] dOperands = new double[] {
    Double.NaN,
    Double.NEGATIVE_INFINITY,
    -12345679, // < -12345678.9
    -12345678, // > -12345678.9
    -33333334,
    -33333333,
    -1234,
    0,
    Double.MAX_VALUE,
    Double.MIN_NORMAL,
    1234,
    12345678, // < -12345678.9
    12345679, // > -12345678.9
    33333333,
    33333334,
    1234.567890123456789000000000000000000000,
    3333.333333333333333333330000000000000000,

    Double.MAX_VALUE,
    Double.POSITIVE_INFINITY,
  };

  public static Object[] qOperands() {
    return qOperands;
  }

  /**
   * returns a two-dimensional array of test data,
   * each item containing two ImmutableQuadruples to compare and integer expected comparison result.
   * Built of cartesian product of (valuesToCompare * valuesToCompare) and comparisonResults
   */
  public static Object[][] toCompareImmQuadruples() {
    final Object[][] result = combineQuadruples();
    supplementWithQuadQuadOperationResults(result, DraftTestData::expectedComparisonResult);
    return result;
  }

  public static Object[][] toCompareQuadruplesWithLongs() {
    final Object[][] result = combineQuadruplesWithLongs();
    supplementWithQuadLongOperationResults(result, DraftTestData::expectedComparisonWithLongResult);
    return result;
  }

  public static Object[][] toCompareQuadruplesWithDoubles() {
    final Object[][] result = combineQuadruplesWithDoubles();
    supplementWithQuadDoubleOperationResults(result, DraftTestData::expectedComparisonWithDoubleResult);
    return result;
  }

  public static Object[] toTestEquality() {
    final Object[][] quadComparisonData = toCompareImmQuadruples();
    // q1.equals(q2) checks not only the values ​​of the operands but also their types,
    // so make an array that contains both ImmutableQuadruple and Double operands
    final Object[][] quadToDoubleComparisonData = toCompareQuadruplesWithDoubles();
    final Object[][] result = Arrays.copyOf(quadComparisonData, quadComparisonData.length + quadToDoubleComparisonData.length);
    System.arraycopy(quadToDoubleComparisonData, 0, result, quadComparisonData.length, quadToDoubleComparisonData.length);

    for (final Object [] data: result) {
      if (data[1] instanceof ImmutableQuadruple
          && (int)data[2] == 0) {
        data[2] = true;
      } else {
        data[2] = false;
      }
    }

    return result;
  }

  public static List<ImmutableQuadruple> randomValues(int count, int randSeed) {
    final List<ImmutableQuadruple> cases = new ArrayList<>();

    final Random rand = new Random(randSeed);
    for (int i = 0; i < count; i++) {
      cases.add(ImmutableQuadruple.construct(rand.nextBoolean(), rand.nextInt(), rand.nextLong(), rand.nextLong()));
    }

    return cases;
  }

  public static Object[][] adjacentPairs() {
    final List<ImmutableQuadruple> list = adjacentValues();
    final Object[][] dataSamples = new Object[list.size() -1][];
    for (int i = 0; i < list.size() - 1; i++) {
      dataSamples[i] = new ImmutableQuadruple[] { list.get(i), list.get(i + 1) };
    }
    return dataSamples;
  }

  public static Object[][] toCompareMagnitudes() {
    final Object[] variousPairs = toCompareImmQuadruples(); // All possible pairs consisting of qOperands
    final int quarterOfSize = variousPairs.length;
    final int fullSize = quarterOfSize * 4;

    // Size x 4 -- both positive, positive + negative, negative + positive, both negative
    final Object[][] result = new Object[fullSize][];
    for (int i = 0; i < 4; i++) {
      // Fill a quarter of the resulting array with pairs from variousPairs, with corrected sign
      // and expected result of magnitude comparison
      fillAQuarter(i, variousPairs, result);
    }
    return result;
  }

  public static Object[][] toFindMax() {
    return toFindExtremum(true);
  }

  public static Object[][] toFindMin() {
    return toFindExtremum(false);
  }

  public static Object[][] toAddImmutableQuadruple() {
    final Object[][] result = combineQuadruples();
    supplementWithQuadQuadOperationResults(result, DraftTestData::expectedQuadQuadAdditionResult);
    return result;
  }

  public static Object[][] toAddLong() {
    final Object[][] result = combineQuadruplesWithLongs();
    supplementWithQuadLongOperationResults(result, DraftTestData::expectedQuadLongAdditionResult);
    return result;
  }

  public static Object[][] toAddDouble() {
    final Object[][] result = combineQuadruplesWithDoubles();
    supplementWithQuadDoubleOperationResults(result, DraftTestData::expectedQuadDoubleAdditionResult);
    return result;
  }

  public static Object[][] toSubtractImmutableQuadruple() {
    final Object[][] result = combineQuadruples();
    supplementWithQuadQuadOperationResults(result, DraftTestData::expectedQuadQuadSubtractionResult);
    return result;
  }

  public static Object[][] toSubtractLong() {
    final Object[][] result = combineQuadruplesWithLongs();
    supplementWithQuadLongOperationResults(result, DraftTestData::expectedQuadLongSubtractionResult);
    return result;
  }

  public static Object[][] toSubtractDouble() {
    final Object[][] result = combineQuadruplesWithDoubles();
    supplementWithQuadDoubleOperationResults(result, DraftTestData::expectedQuadDoubleSubtractionResult);
    return result;
  }

  //###########################################################
  // Private helper methods

  private static Object[][] combineQuadruples() {
    final int size = qOperands.length;
    final Object[][] result = new Object[size * size][];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        final int index = i * size + j;
        result[index] = new Object[] {qOperands[i], qOperands[j], null};
      }
    }
    return result;
  }

  private static Object[][] combineQuadruplesWithLongs() {
    final int size1 = qOperands.length;
    final int size2 = lOperands.length;
    final Object[][] result = new Object[size1 * size2][];
    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        final int index = i * size2 + j;
        result[index] = new Object[] { qOperands[i], lOperands[j], null };
      }
    }
    return result;
  }

  private static Object[][] combineQuadruplesWithDoubles() {
    final int size1 = qOperands.length;
    final int size2 = dOperands.length;
    final Object[][] result = new Object[size1 * size2][];
    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        final int index = i * size2 + j;
        result[index] = new Object[] { qOperands[i], dOperands[j], null };
      }
    }
    return result;
  }

  private static void supplementWithQuadQuadOperationResults(Object[][] result,
      BiFunction<ImmutableQuadruple, ImmutableQuadruple, Object> biFunction) {
    for (final Object[] dataSample: result) {
      dataSample[2] = biFunction.apply((ImmutableQuadruple)dataSample[0], (ImmutableQuadruple)dataSample[1]);
    }
  }

  private static void supplementWithQuadLongOperationResults(Object[][] result,
      BiFunction<ImmutableQuadruple, Long, Object> biFunction) {
    for (final Object[] dataSample: result) {
      dataSample[2] = biFunction.apply((ImmutableQuadruple)dataSample[0], (Long)dataSample[1]);
    }
  }

  private static void supplementWithQuadDoubleOperationResults(Object[][] result,
      BiFunction<ImmutableQuadruple, Double, Object> biFunction) {
    for (final Object[] dataSample: result) {
      dataSample[2] = biFunction.apply((ImmutableQuadruple)dataSample[0], (Double)dataSample[1]);
    }
  }

  private static List<ImmutableQuadruple> adjacentValues() {
    final List<ImmutableQuadruple> cases = new ArrayList<>();

    final int initialExponent = 0x7FFF_FFFF;
    final long initialMantLo = 12345L;
    final long initialMantHi = 0x8765_bad0_0000_0000L;

    // Adjacent values of exponent
    int exponent = initialExponent - 50;
    for (int i = 0; i < 100; i++) {
      exponent++;
      cases.add(ImmutableQuadruple.construct(false, exponent, initialMantHi, initialMantLo));
      cases.add(ImmutableQuadruple.construct(true, exponent, initialMantHi, initialMantLo));
    }

    // Adjacent values of mantHi
    long mant = initialMantHi;
    for (int i = 0; i < 100; i++) {
      mant++;
      cases.add(ImmutableQuadruple.construct(false, initialExponent, mant, initialMantLo));
      cases.add(ImmutableQuadruple.construct(true, initialExponent, mant, initialMantLo));
    }

    // Adjacent values of mantHi
    mant = initialMantLo;
    for (int i = 0; i < 100; i++) {
      mant++;
      cases.add(ImmutableQuadruple.construct(false, initialExponent, initialMantHi, mant));
      cases.add(ImmutableQuadruple.construct(true, initialExponent, initialMantHi, mant));
    }

    return cases;
  }



  private static Object[][] toFindExtremum(boolean findingMax) {
    final Object[] variousPairs = toCompareImmQuadruples(); // All possible pairs consisting of qOperands
    final Object[][] result = new Object[variousPairs.length][];

    for (int i = 0; i < variousPairs.length; i++) {
      final Object[] pair = (Object[])variousPairs[i];
      final ImmutableQuadruple q1 = (ImmutableQuadruple)pair[0];
      final ImmutableQuadruple q2 = (ImmutableQuadruple)pair[1];
      ImmutableQuadruple extremum;
      if (findingMax) {
        extremum = q1.compareTo(q2) >= 0? q1 : q2; // Already tested
      } else {
        extremum = q1.compareTo(q2) < 0? q1 : q2; // Already tested
      }
      result[i] = new Object[] { q1, q2, extremum };
    }
    return result;

  }

  private static void fillAQuarter(int quarterNumber, Object[] variousPairs, Object[][] result) {
    final int quarterSize = variousPairs.length;
    int startIdx = quarterNumber * quarterSize;
    for (int i = 0; i < quarterSize; i++) {
      result[startIdx++] = changeSignsAndFindExpected(quarterNumber,  variousPairs[i]);
    }
  }

  private static Object[] changeSignsAndFindExpected(int quarterNumber, Object aPair) {
    final Object[] pair = (Object[]) aPair;
    final ImmutableQuadruple q1 = (ImmutableQuadruple)(pair[0]);
    final ImmutableQuadruple q2 = (ImmutableQuadruple)(pair[1]);
    final Object[] resultItem = new Object[3]; // First number, second number, and expected result of comapreMagnitudes

    if ((quarterNumber & 2) != 0) { // The first number must be negative
      resultItem[0] = negativeOf(q1);
    } else {
      resultItem[0] = positiveOf(q1);
    }

    if ((quarterNumber & 1) != 0) { // The second number must be negative
      resultItem[1] = negativeOf(q2);
    } else {
      resultItem[1] = positiveOf(q2);
    }

    resultItem[2] = compareMagnitude(resultItem[0], resultItem[1]);
    return resultItem;
  }

  private static ImmutableQuadruple negativeOf(ImmutableQuadruple q) {
    return q.abs().negate();
  }

  private static ImmutableQuadruple positiveOf(ImmutableQuadruple q) {
    return q.abs();
  }

  private static Integer compareMagnitude(Object q1Obj, Object q2Obj) {
    final ImmutableQuadruple q1 = (ImmutableQuadruple)q1Obj;
    final ImmutableQuadruple q2 = (ImmutableQuadruple)q2Obj;
    if (q1.isNaN()) {
      return q2.isNaN()? 0 : 1;
    }
    if (q2.isNaN()) {
      return -1;
    }
    if (q1.isInfinite()) {
      return q2.isInfinite()? 0 : 1;
    }
    if (q2.isInfinite()) {
      return -1;
    }

    final BigDecimal bd1 = (q1.bigDecimalValue()).abs();
    final BigDecimal bd2 = (q2.bigDecimalValue()).abs();
    return bd1.compareTo(bd2);
  }

  private static int expectedComparisonResult(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    if (q1.isNaN()) {
      if (q2.isNaN()) {
        return 0;
      } else {
        return 1;
      }
    } else if (q2.isNaN()) {
      return -1;
    } else if (q1.isInfinite() && !q1.isNegative()) { // None is NaN, +Infinity?
      if (q2.isInfinite() && !q2.isNegative()) {
        return 0;
      } else {
        return 1;   // Infinity is greater than anything except Infinity
      }
    } else if (q1.isInfinite() && q1.isNegative()) { // None is neither NaN nor +Infinity
      if (q2.isInfinite() && q2.isNegative()) {
        return 0;
      } else {
        return -1;  // -Infinity is less than anything except -Infinity
      }
    } else if (q2.isInfinite() && !q2.isNegative()) {
      return -1;
    } else if (q2.isInfinite() && q2.isNegative()) {
      return 1;
    }

    final BigDecimal bd1 = new BigDecimal(q1.toString());
    final BigDecimal bd2 = new BigDecimal(q2.toString());
    return bd1.compareTo(bd2);
  }

  private static int expectedComparisonWithLongResult(ImmutableQuadruple q1, Long q2) {
    if (q1.isNaN()) {
      return 1;
    } else if (q1.isInfinite() && !q1.isNegative()) { // None is NaN, +Infinity?
      return 1;   // Infinity greater than anything except Infinity
    } else if (q1.isInfinite() && q1.isNegative()) { // None is neither NaN nor +Infinity
      return -1;
    }

    final BigDecimal bd1 = new BigDecimal(q1.toString());
    final BigDecimal bd2 = new BigDecimal(q2.toString());
    return bd1.compareTo(bd2);
  }

  private static int expectedComparisonWithDoubleResult(ImmutableQuadruple q1, Double q2) {
    if (q1.isNaN()) {
      if (Double.isNaN(q2)) {
        return 0;
      } else {
        return 1;
      }
    } else if (Double.isNaN(q2)) {
      return -1;
    } else if (q1.isInfinite() && !q1.isNegative()) { // None is NaN, +Infinity?
      if (q2 == Double.POSITIVE_INFINITY) {
        return 0;
      } else {
        return 1;   // Infinity is greater than anything except Infinity
      }
    } else if (q1.isInfinite() && q1.isNegative()) { // None is neither NaN nor +Infinity
      if (q2 == Double.NEGATIVE_INFINITY) {
        return 0;
      } else {
        return -1;  // -Infinity is less than anything except -Infinity
      }
    } else if (q2 == Double.NEGATIVE_INFINITY) {
      return 1;
    } else if (q2 == Double.POSITIVE_INFINITY) {
      return -1;
    }

    final String s1 = q1.toString();
    final BigDecimal bd1 = new BigDecimal(s1);
    final BigDecimal bd2 = new BigDecimal(q2, MC_50); // Exact value of the double, up to 50 digits
    return bd1.compareTo(bd2);
  }

  private static ImmutableQuadruple expectedQuadQuadAdditionResult(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    // Corner cases
    if (q1.isNaN() || q2.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN + anything = NaN
    }

    if (q1.isInfinite()) {
      if (q2.isInfinite() && (q1.isNegative() != q2.isNegative()))
        return ImmutableQuadruple.NaN;    // -Infinity + Infinity = NaN
      else return q1;                     // Infinity + X = Infinity
    }

    if (q2.isInfinite()) return q2;       // x + Infinity = Infinity regardless of their signs

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();
    final BigDecimal bd2 = q2.bigDecimalValue();
    final BigDecimal sum = bd1.add(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

  private static ImmutableQuadruple expectedQuadLongAdditionResult(ImmutableQuadruple q1, Long longSummand) {
    // Corner cases
    if (q1.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN + anything = NaN
    }

    if (q1.isInfinite()) {
      return q1;                     // Infinity + X = Infinity
    }

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();
    final BigDecimal bd2 = BigDecimal.valueOf(longSummand);
    final BigDecimal sum = bd1.add(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

  private static ImmutableQuadruple expectedQuadDoubleAdditionResult(ImmutableQuadruple q1, Double doubleSummand) {
    // Corner cases
    if (q1.isNaN() || doubleSummand.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN + anything = NaN
    }

    if (q1.isInfinite()) {
      if (doubleSummand.isInfinite() && (q1.isNegative() != (doubleSummand < 0)))
        return ImmutableQuadruple.NaN;    // -Infinity + Infinity = NaN
      else return q1;                     // Infinity + X = Infinity
    }

    if (doubleSummand.isInfinite()) {
      if (doubleSummand < 0) {
        return ImmutableQuadruple.NEGATIVE_INFINITY;
      } else {
        return ImmutableQuadruple.POSITIVE_INFINITY;
      }
    }

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();

    // final BigDecimal bd2 = BigDecimal.valueOf(doubleSummand);
    /* We expect the addition to use not the string representation (rounded),
     * but the exact value of the Double summand (which in general cannot be
     * expressed exactly as a finite decimal number).     */
    final BigDecimal bd2 = new BigDecimal(doubleSummand, MC_120);

    final BigDecimal sum = bd1.add(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

  private static ImmutableQuadruple expectedQuadQuadSubtractionResult(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    // Corner cases
    if (q1.isNaN() || q2.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN + anything = NaN
    }

    if (q1.isInfinite()) {
      if (q2.isInfinite() && (q1.isNegative() != q2.isNegative())) { // Both infinite, different signs
        return q1;                      // -Inf - Inf = -Inf, +Inf - (-Inf) = +Inf
      } else if (q2.isInfinite()) {     // Same sign
        return ImmutableQuadruple.NaN;  // -Inf - (-Inf) = NaN, +Inf - (+Inf) = NaN
      } else {
        return q1;                      // Infinity - x = infinity
      }
    }

    if (q2.isInfinite()) return q2.negate(); // x - Inf = -Inf, x - (-Inf) = +Inf

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();
    final BigDecimal bd2 = q2.bigDecimalValue();
    final BigDecimal sum = bd1.subtract(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

  private static ImmutableQuadruple expectedQuadLongSubtractionResult(ImmutableQuadruple q1, Long longSubtrahend) {
    // Corner cases
    if (q1.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN - anything = NaN
    }

    if (q1.isInfinite()) {
      return q1;                     // Infinity - X = Infinity
    }

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();
    final BigDecimal bd2 = BigDecimal.valueOf(longSubtrahend);
    final BigDecimal sum = bd1.subtract(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

  private static ImmutableQuadruple expectedQuadDoubleSubtractionResult(ImmutableQuadruple q1, Double doubleSubtrahend) {
    // Corner cases
    if (q1.isNaN() || doubleSubtrahend.isNaN()) {
      return ImmutableQuadruple.NaN;      // NaN - anything = NaN
    }

    if (q1.isInfinite()) {
      if (doubleSubtrahend.isInfinite() && (q1.isNegative() != (doubleSubtrahend < 0))) { // Both infinite, different signs
        return q1;                      // -Inf - Inf = -Inf, +Inf - (-Inf) = +Inf
      } else if (doubleSubtrahend.isInfinite()) {     // Same sign
        return ImmutableQuadruple.NaN;  // -Inf - (-Inf) = NaN, +Inf - (+Inf) = NaN
      } else {
        return q1;                      // Infinity - x = infinity
      }
    }

    if (doubleSubtrahend.isInfinite()) {
      if (doubleSubtrahend < 0) {
        return ImmutableQuadruple.POSITIVE_INFINITY; // x - (-Inf) = +Inf
      } else {
        return ImmutableQuadruple.NEGATIVE_INFINITY; // x - Inf = -Inf
      }
    }

    // Regular numeric values
    final BigDecimal bd1 = q1.bigDecimalValue();

    // final BigDecimal bd2 = BigDecimal.valueOf(doubleSummand);
    /* We expect the addition to use not the string representation (rounded),
     * but the exact value of the Double summand (which in general cannot be
     * expressed exactly as a finite decimal number).     */
    final BigDecimal bd2 = new BigDecimal(doubleSubtrahend, MC_120);

    final BigDecimal sum = bd1.subtract(bd2, MC_120);
    return new ImmutableQuadruple(sum);
  }

}
