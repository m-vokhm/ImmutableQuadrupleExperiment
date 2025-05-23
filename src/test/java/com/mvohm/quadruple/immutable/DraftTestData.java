package com.mvohm.quadruple.immutable;

import static com.mvohm.quadruple.immutable.test.AuxMethods.MC_50;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    new ImmutableQuadruple( "6.672829482607474308148353774991346115977e-646457032"),
    new ImmutableQuadruple( "1.761613051683963353207493149791840285665e+646456993"),
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
    new ImmutableQuadruple( "3.333333333333333333333330000000000000000e+37"),
    new ImmutableQuadruple("-1.234567890123456789000000000000000000000e+37"),
    new ImmutableQuadruple("-3.333333333333333333333330000000000000000e+37"),
    new ImmutableQuadruple( "1.234567890123456789000000000000000000000e-37"),
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

  static Object[] qOperands() {
    return qOperands;
  }

  /**
   * returns a two-dimensional array of test data,
   * each item containing two ImmutableQuadruples to compare and integer expected comparison result.
   * Built of cartesian product of (valuesToCompare * valuesToCompare) and comparisonResults
   */
  static Object[] toCompareImmQuadruples() {
    final int size = qOperands.length;
    final Object[][] result = new Object[size * size][];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        final int index = i * size + j;
        result[index] = new Object[] {
          qOperands[i], qOperands[j], DraftTestData.expectedComparisonResult(qOperands[i], qOperands[j]),
        };
      }
    }
    return result;
  }

  static Object[] toCompareQuadruplesWithLongs() {
    final int size1 = qOperands.length;
    final int size2 = lOperands.length;
    final Object[][] result = new Object[size1 * size2][];
    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        final int index = i * size2 + j;
        result[index] = new Object[] {
          qOperands[i], lOperands[j], DraftTestData.expectedComparisonWithLongResult(qOperands[i], lOperands[j]),
        };
      }
    }
    return result;
  }

  static Object[] toCompareQuadruplesWithDoubles() {
    final int size1 = qOperands.length;
    final int size2 = dOperands.length;
    final Object[][] result = new Object[size1 * size2][];
    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        final int index = i * size2 + j;
        result[index] = new Object[] {
          qOperands[i], dOperands[j], DraftTestData.expectedComparisonWithDoubleResult(qOperands[i], dOperands[j]),
        };
      }
    }
    return result;
  }

  static Object[] toTestEquality() {
    final Object[] quadComparisonData = toCompareImmQuadruples();
    final Object[] quadToDoubleComparisonData = toCompareQuadruplesWithDoubles();
    final Object[] result = Arrays.copyOf(quadComparisonData, quadComparisonData.length + quadToDoubleComparisonData.length);
    System.arraycopy(quadToDoubleComparisonData, 0, result, quadComparisonData.length, quadToDoubleComparisonData.length);
    for (int i = 0; i < result.length; i++) {
      final Object[] data = (Object[])(result[i]);
      if (data[1] instanceof ImmutableQuadruple
          && (int)data[2] == 0) {
        data[2] = true;
      } else {
        data[2] = false;
      }
    }
    return result;
  }

  public static List<ImmutableQuadruple> adjacentValues() {
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

  //###########################################################
  // Private helper methods

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

  private static int expectedComparisonWithDoubleResult(ImmutableQuadruple q1, double q2) {
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

}
