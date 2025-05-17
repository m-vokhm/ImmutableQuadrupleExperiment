package com.mvohm.quadruple;

import java.math.BigDecimal;
import java.util.Random;

import com.mvohm.quadruple.Quadruple;

public class ImmutableQuadruple extends Number implements Comparable<ImmutableQuadruple> {

  private final Quadruple value;

  /**
   * Creates a new {@code ImmutableQuadruple} instance with value of 0.0 .<br>
   * Actually, same as ImmutableQuadruple.ZERO
   */
  public ImmutableQuadruple() {
    value = new Quadruple();
  }

  /**
   * Creates a new {@code ImmutableQuadruple} instance with the value of the given {@code ImmutableQuadruple} instance.<br>
   * First creates an empty (zero) instance, then copies the fields of the parameter.
   * to the fields of the new instance
   * @param iqValue the {@code ImmutableQuadruple} value to be assigned to the new instance.
   */
  private ImmutableQuadruple(ImmutableQuadruple iqValue) {
    value = new Quadruple(iqValue.value);
  }

  /**
   *  Creates a new {@code ImmutableQuadruple} instance with the given {@code double} value.<br>
   * First creates an empty (zero) instance, then assigns the given
   * value to the new instance, using {@link #assign(double)}.
   * @param dValue  the {@code double} value to be assigned
   */
  public ImmutableQuadruple(double dValue) {
    value = new Quadruple(dValue);
  }

  /**
   * Creates a new {@code ImmutableQuadruple} with the given {@code long} value.<br>
   * First creates an empty (zero) instance, then assigns the given
   * value to the new instance, using {@link #assign(long)}.
   * @param lValue  the {@code long} value to be assigned */
  public ImmutableQuadruple(long lValue) {
    value = new Quadruple(lValue);
  }

  /**
   * Creates a new {@code ImmutableQuadruple} with the value represented by the given {@code String}.<br>
   * First creates an empty (zero) instance, then assigns the given
   * value to the new instance, converting the string to the corresponding floating-point value.
   * Some non-standard string designations for special values are admissible, see {@link #assign(String)}
   * @param strValue the {@code String} value to be assigned
   * @see #assign(String)
   */
  public ImmutableQuadruple(String strValue) {
    value = new Quadruple(strValue);
  }

  /**
   * Creates a new {@code ImmutableQuadruple} with the value of the given {@code BigDecimal} instance.<br>
   * First creates an empty (zero) instance, then assigns the given
   * value to the new instance, converting the BigDecimal to respective floating-point value
   * @param bdValue the {@code BigDecimal} value to be assigned
   */
  public ImmutableQuadruple(BigDecimal bdValue) {
    value = new Quadruple(bdValue);
  }

  public static ImmutableQuadruple construct(boolean sign, int exponent, long mantHi, long mantLo) {
    return new ImmutableQuadruple(new Quadruple(sign, exponent, mantHi, mantLo));
  }

  private ImmutableQuadruple(Quadruple value) {
    this.value = value;
  }

  public static final ImmutableQuadruple ZERO              = new ImmutableQuadruple();
  public static final ImmutableQuadruple NEGATIVE_INFINITY = new ImmutableQuadruple(Quadruple.negativeInfinity());
  public static final ImmutableQuadruple POSITIVE_INFINITY = new ImmutableQuadruple(Quadruple.positiveInfinity());
  public static final ImmutableQuadruple NaN               = new ImmutableQuadruple(Quadruple.nan());
  public static final ImmutableQuadruple ONE               = new ImmutableQuadruple(Quadruple.one());
  public static final ImmutableQuadruple TWO               = new ImmutableQuadruple(Quadruple.two());
  public static final ImmutableQuadruple TEN               = new ImmutableQuadruple(Quadruple.ten());
  public static final ImmutableQuadruple MIN_VALUE         = new ImmutableQuadruple(Quadruple.minValue());
  public static final ImmutableQuadruple MIN_NORMAL        = new ImmutableQuadruple(Quadruple.minNormal());
  public static final ImmutableQuadruple MAX_VALUE         = new ImmutableQuadruple(Quadruple.maxValue());
  public static final ImmutableQuadruple PI                = new ImmutableQuadruple(Quadruple.pi());

  protected void ____Getters_for_private_fields____() {} // Just to put a visible mark of the section in the outline view of the IDE

  /**  Returns the raw (biased) value of the binary exponent of the value
   * i. e. 0x7FFF_FFFF for values falling within the interval of {@code [1.0 .. 2.0)}, 0x8000_0000 for {@code [2.0 .. 4.0)} etc.
   * @return the raw (biased) value of the binary exponent of the value
   */
  public int exponent() { return value.exponent(); }

  /**
   *  Returns the unbiased value of binary exponent,
   * i. e. 0 for values falling within the interval of {@code [1.0 .. 2.0)}, 1 for {@code [2.0 .. 4.0)} etc.
   * @return the unbiased value of binary exponent */
  public int unbiasedExponent() { return value.exponent() - Quadruple.EXPONENT_BIAS; }

  /**
   * Returns the most significant 64 bits of the fractional part of the mantissa.
   * @return the most significant 64 bits of the fractional part of the mantissa
   */
  public long mantHi() { return value.mantHi(); }

  /**
   * Returns the least significant 64 bits of the fractional part of the mantissa
   * @return the least significant 64 bits of the fractional part of the mantissa */
  public long mantLo() { return value.mantLo(); }

  /**
   * Checks if the value is negative.
   * @return {@code true}, if the value is negative, {@code false} otherwise  */
  public boolean isNegative() { return value.isNegative(); }

  /**
   * Checks if the value is infinite (i.e {@code NEGATIVE_INFINITY} or {@code POSITIVE_INFINITY}).
   * @return {@code true}, if the value is infinity (either positive or negative), {@code false} otherwise */
  public boolean isInfinite() {
    return (value.exponent() == Quadruple.EXPONENT_OF_INFINITY) && ((value.mantHi() | value.mantLo()) == 0);
  }

  /** Checks if the value is not a number (i.e. has the value of {@code NaN}).
   * @return {@code true}, if the value is not a number (NaN), {@code false} otherwise */
  public boolean isNaN() {
    return (value.exponent() == Quadruple.EXPONENT_OF_INFINITY) && ((value.mantHi() | value.mantLo()) != 0);
  }

  /**
   * Checks if the value is zero, either positive or negative.
   * @return {@code true}, if the value is 0 or -0, otherwise returns   */
  public boolean isZero() {
    return (value.mantHi() | value.mantLo() | value.exponent()) == 0;
  }

  protected void ____Conversions____() {} // Just to put a visible mark of the section in the outline view of the IDE

  /**
   * Converts the value of this {@code ImmutableQuadruple} to an {@code int} value in a way
   * similar to standard narrowing conversions (e.g., from {@code double} to {@code int}).
   * @return the value of this {@code ImmutableQuadruple} instance converted to an {@code int}.
   * */
  @Override
  public int intValue() {
    return value.intValue();
  }

  /** Converts the value of this {@code ImmutableQuadruple} to a {@code long} value in a way
   * similar to standard narrowing conversions (e.g., from {@code double} to {@code long}).
   * @return the value of this {@code ImmutableQuadruple} instance converted to a {@code long}.
   */
  @Override
  public long longValue() {
    return value.longValue();
  } // public long longValue() {

  /** Converts the value of this {@code ImmutableQuadruple} to a {@code float} value in a way
   * similar to standard narrowing conversions (e.g., from {@code double} to {@code float}).
   * @return the value of this {@code ImmutableQuadruple} instance converted to a {@code float}.
   * */
  @Override
  public float floatValue() {
    return (float)(value.doubleValue());
  } // public float floatValue() {

  /** Converts the value of this {@code ImmutableQuadruple} to a {@code double} value in a way
   * similar to standard narrowing conversions (e.g., from {@code double} to {@code float}).
   * Uses 'half-even' approach to the rounding, like {@code BigDecimal.doubleValue()}
   * @return the value of this {@code ImmutableQuadruple} instance converted to a {@code double}.
   * */
  @Override
  public double doubleValue() {
    return value.doubleValue();
  }

  /**
   * Builds and returns a {@code BigDecimal} instance holding the same value as the given ImmutableQuadruple
   * (rounded to 100 significant decimal digits).
   * @return a {@code BigDecimal} holding the same value as the given {@code ImmutableQuadruple}
   * @throws NumberFormatException if the value of the instance is not convertible to {@code BigDecimal}
   * (i.e. it is {@code Infinity}, {@code -Infinity},  or {@code NaN})
   */
  public BigDecimal bigDecimalValue() throws NumberFormatException {
    return value.bigDecimalValue();
  }

  /**
   * Returns a decimal string representation of the value of this {@code ImmutableQuadruple}
   * in a scientific (exponential) notation, rounded to 43 digits after point.<br>
   * For other String representations, see {@code format(String)}
   * @see #format(String)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return value.toString();
  } // public String toString()

  /**
   * Returns a {@code String} representing the value
   * of this instance in a form defined by the {@code format} parameter.
   * If the value is NaN or +/-Infinity, returns respectively "NaN", "Infinity", or "-Infinity",
   * otherwise formats the value in accordance with the rules
   * used for formatting {@code BigDecimal} values, like in String.format("%9.3f", value).
   * @param format A pattern to format the value
   * @return a {@code String} representation of this value, formatted in accordance with the
   * {@code format} parameter
   */
  public String format(String format) {
    return value.format(format);
  }

  /**
   * Returns a {@code String} containing a hexadecimal representation
   * of the instance's value, consisting of sign, two 64-bit words of mantissa, and
   * exponent preceded by letter 'e', with '_' separating the tetrads of hexadecimal digits.
   * This way, the value -1.5 is represented by the string
   * {@code -8000_0000_0000_0000 0000_0000_0000_0000 e7fff_ffff}
   * @return a string containing a hexadecimal representation
   */
  public String toHexString() {
    return value.toHexString();
  }

  /**
   * Returns the fields of the instance that make up it's value as
   * an array of {@code long}s.<br>
   * The elements of the array contain the following values:<pre> {@code
   * value[0] -- sign flag in bit 63 (1 means negative),
   *             biased exponent in bits 31 -- 0
   * value[1] -- The higher 64 bits of the fractional part of the mantissa
   * value[2] -- The lower 64 bits of the fractional part of the mantissa}</pre>
   * @return an array of 3 {@code long}s containing the contents of the
   * instance's fields, as described above
   * @see #assign(long[])
   */
  public long[] toLongWords() {
    return value.toLongWords();
  } // public long[] toLongWords() {

  /**
   * Returns the 128 bits of an IEEE-754 quadruple precision number nearest to the value
   * of {@code this} instance as an array of two {@code long}s, containing a physical representation
   * of the standard IEEE-754 quadruple-precision floating-point number.<br>
   * The order of words is big-endian, so that the sign bit, exponent
   * and 48 most significant bits of the mantissa are returned in result[0],
   * and 64 least significant bits of the mantissa in result[1].
   * The 128-bit significand of this instance is rounded to fit to the 112 bits of the
   * IEEE-754 quadruple. The rounding mode is half-up, i.e. if the exact value of the instance
   * differs from the nearest IEEE-754 quadruple value by 1/2 of LSB of the IEEE-754
   * quadruple's significand, it gets rounded up.
   * The values whose magnitude exceed the maximum possible value of IEEE-754 Quadruple
   * (namely, 1.18973149535723176508575932662800702 * 10^4932) plus half of its mantissa'a LSB
   * are converted to {@code Infinity} or {@code -Infinity}, depending on the sign,
   * the values with magnitudes less than minimum normal IEEE-754 quadruple value
   * ({@code 3.36210314311209350626267781732175260 * 10^-4932})
   * but greater or equal to {@code 6.4751751194380251109244389582276466 * 10^-4966}
   * are converted to subnormal IEEE-754 values, and the values whose magnitude is less
   * than {@code 6.4751751194380251109244389582276466 * 10^-4966} (minimum positive value of of IEEE-754 Quadruple)
   * are converted to 0 or -0, depending on the sign of {@code this} instance.
   *
   * @return an array of two longs containing the 128 bits of the IEEE-745 Quadruple
   * value nearest to the value of this instance.
   */
  public long[] toIeee754Longs() {
    return value.toIeee754Longs();
  }

  /**
   * Returns the 128 bits of an IEEE-754 quadruple precision number nearest to the value
   * of {@code this} instance as an array of 16 {@code byte}s, containing a physical representation
   * of the standard IEEE-754 quadruple-precision floating-point number.<br>
   * The order of bytes is big-endian, so that the sign bit and the most significant bits
   * of the exponent is returned in result[0], and the least significant bits
   * of the mantissa in result[15].
   * The 128-bit significand of this instance is rounded to fit to the 112 bits of the
   * IEEE-754 quadruple. The rounding mode is half-up, i.e. if the exact value of the instance
   * differs from the nearest IEEE-754 quadruple value by 1/2 of LSB of the IEEE-754
   * quadruple's significand, it gets rounded up.
   * The values whose magnitude exceed the maximum possible value of IEEE-754 Quadruple
   * (namely, 1.18973149535723176508575932662800702 * 10^4932) plus half of its mantissa'a LSB
   * are converted to {@code Infinity} or {@code -Infinity}, depending on the sign,
   * the values with magnitudes less than {@code 3.36210314311209350626267781732175260 * 10^-4932}
   * but greater or equal to {@code 6.4751751194380251109244389582276466 * 10^-4966}
   * are converted to subnormal IEEE-754 values,
   * and the values whose magnitude is less than {@code 6.4751751194380251109244389582276466 * 10^-4966}
   * (minimum positive value of of IEEE-754 Quadruple)
   * are converted to 0 or -0, depending on the sign of {@code this} instance.
   *
   * @return an array of bytes containing the value of {@code this} instance
   * as a physical representation of the nearest IEEE-745 Quadruple value,
   * in the big-endian order.
   */
  public byte[] toIeee754Bytes() {
    return value.toIeee754Bytes();
  }


  /* ***********************************************************************************
   ****** Comparisons ******************************************************************
   *********************************************************************************** */

  protected void ____Comparisons____() {} // Just to put a visible mark of the section in the outline view of the IDE

  /**
   * Compares the value of this instance with the value of the specified instance.
   * @param other the {@code ImmutableQuadruple} to compare with
   * @return a negative integer, zero, or a positive integer as the value of this instance is less than,
   * equal to, or greater than the value of the specified instance.
   */
  @Override
  public int compareTo(ImmutableQuadruple other) {
    return value.compareTo(other.value);
  }

  /**
   * Compares the value of this instance with the specified {@code long} value.
   * The value of the argument is converted to Quadruple, and then
   * the value of this instance is compared with the value of the argument by {@link #compareTo(Quadruple)}
   * @param other the {@code long} value to compare with
   * @return a negative integer, zero, or a positive integer as the value of this instance is less than,
   * equal to, or greater than the specified {@code long} value.
   */
  public int compareTo(long other) {
    return value.compareTo(other);
  }

  /**
   * Compares the value of this instance with the specified {@code double} value.
   * The value of the argument is converted to Quadruple,
   * and then the {@code Quadruple} value of this instance
   * is compared with the value of the argument by {@link #compareTo(Quadruple)}
   * @param other the {@code double} value to compare with
   * @return a negative integer, zero, or a positive integer as the value of this instance is less than,
   * equal to, or greater than the specified {@code double} value.
   */
  public int compareTo(double other) {
    return value.compareTo(other);
  }

  /**
   * Indicates whether the other {@code ImmutableQuadruple} is equal to this one.
   * @param obj the object to compare with
   * @return {@code true} if the given object is ImmutableQuadruple and its value is equal to
   * the value of this {@code ImmutableQuadruple} instance, {@code false} otherwise.
   *
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ImmutableQuadruple)) return false;
    return value.equals(((ImmutableQuadruple)obj).value);
  }

  /** Computes a hashcode for this {@code ImmutableQuadruple},
   * based on the values of its fields.
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return value.hashCode();
  }

  /**
   * Compares the values of two instances.
   * @param q1 the instance to compare with the other one
   * @param q2 the instance to compare with
   * @return a negative integer, zero, or a positive integer as the value of the first
   * instance is less than, equal to, or greater than the value of the second instance.
   */
  public static int compare(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    return q1.compareTo(q2);
  }

  /**
   * Compares the magnitude (absolute value) of this instance
   * with the magnitude of the other instance.
   * @param other the ImmutableQuadruple to compare with
   * @return 1 if this instance is greater in magnitude than the {@code other} instance,
   * 0 if the argument is equal in magnitude to this instance, -1 if this instance is less in magnitude, than the argument
   *
   */
  public int compareMagnitudeTo(ImmutableQuadruple other) {
    return value.compareMagnitudeTo(other.value);
  }

  /**
   * Compares the magnitudes (absolute values) of the two Quadruples.
   * @param q1 the instance to compare with the other one
   * @param q2 the instance to compare with
   * @return a negative integer, zero, or a positive integer as the magnitude of the first
   * instance is less than, equal to, or greater than the magnitude of the second instance.
   */
  public static int compareMagnitudes(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    return q1.compareMagnitudeTo(q2);
  }

  /**
   * Returns a new instance of {@code ImmutableQuadruple} with the value of the
   * maximum of the values of the operands.
   * @param q1 first operand to compare
   * @param q2 first operand to compare
   * @return the argument whose value is
   *      equal to the value of the greater of the operands.
   */
  public static ImmutableQuadruple max(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    if (q1.compareTo(q2) > 0)
      return q1;
    else
      return q2;
  }

  /**
   * Returns a new instance of {@code ImmutableQuadruple} with the value of the
   * minimum of the values of the operands.
   * @param q1 first operand to compare
   * @param q2 first operand to compare
   * @return the argument whose value is
   *      equal to the value of the lesser of the operands.
   */
  public static ImmutableQuadruple min(ImmutableQuadruple q1, ImmutableQuadruple q2) {
    if (q1.compareTo(q2) < 0)
      return q1;
    else
      return q2;
  }

  /* ***********************************************************************************
   ****** Arithmetics ******************************************************************
   *********************************************************************************** */

  protected void ________Arithmetic_________ () {} // Just to put a visible mark of the section in the outline view of the IDE

  /**
   * Adds the value of the given {@code ImmutableQuadruple} summand to the value of this instance
   * and returns a new instance containing the sum.
   * @param summand the value to add
   * @return the reference to a object, which holds the sum of this instance's value and the value of the summand
   */
  public ImmutableQuadruple add(ImmutableQuadruple summand) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.add(summand.value);
    return newInstance;
  }

  /**
   * Adds the value of the given {@code long} summand to the value of this instance.
   * The value of the {@code long} operand is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires the new value that equals the sum of the previous value and the value of the summand.
   * @param summand the value to add
   * @return a new {@code ImmutableQuadruple} instance which holds the sum of this instance's value and the value of the summand
   */
  public ImmutableQuadruple  add(long summand) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.add(summand);
    return newInstance;
  }

  /**
   * Adds the value of the given {@code double} summand to the value of this Quadruple.
   * The value of the {@code double} operand is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires the new value that equals the sum of the previous value and the value of the summand.
   * @param summand the value to add
   * @return the reference to this object, which holds a new value that equals
   * the sum of its previous value and the value of the summand
   */
  public ImmutableQuadruple  add(double summand) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.add(summand);
    return newInstance;
  }

  /** Adds the value of the given {@code Quadruple op2} to the value of {@code Quadruple op1}
   * and creates a new instance of Quadruple containing the sum.
   * The operands remain unchanged.
   * @param op1 the first operand to add
   * @param op2 the second operand to add
   * @return a new instance of Quadruple containing the sum of the operands
   */
  public static ImmutableQuadruple add(ImmutableQuadruple op1, ImmutableQuadruple op2) {
    return op1.add(op2);
  } // public static Quadruple add(Quadruple op1, Quadruple op2) {

  /** Adds the value of the given {@code long op2} to the value of {@code Quadruple op1}
   * and creates a new instance of Quadruple containing the sum.
   * The value of the {@code long} operand is preliminarily converted to a {@code Quadruple} value.
   * The Quadruple operand remains unchanged.
   * @param op1 the first operand to add
   * @param op2 the second operand to add
   * @return a new instance of Quadruple containing the sum of the operands
   */
  public static ImmutableQuadruple add(ImmutableQuadruple op1, long op2) {
    return op1.add(op2);
  }

  /** Adds the value of the given {@code double op2} to the value of {@code Quadruple op1}
   * and creates a new instance of Quadruple containing the sum.
   * The value of the {@code double} operand is preliminarily converted to a {@code Quadruple} value.
   * The Quadruple operand remains unchanged.
   * @param op1 the first operand to add
   * @param op2 the second operand to add
   * @return a new instance of Quadruple containing the sum of the operands
   */
  public static ImmutableQuadruple add(ImmutableQuadruple op1, double op2) {
    return op1.add(op2);
  }

  /**
   * Subtracts the value of the given {@code Quadruple} subtrahend from the value of this Quadruple.
   * The instance acquires a new value that equals the difference between the previous value and the value of the subtrahend.
   * @param subtrahend the value to be subtracted from the current value of this Quadruple
   * @return the reference to this object, which holds a new value that equals
   * the difference between its previous value and the value of the subtrahend
   */
  public ImmutableQuadruple subtract(ImmutableQuadruple subtrahend) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.subtract(subtrahend.value);
    return newInstance;
  }

  /**
   * Subtracts the value of the given {@code long} subtrahend from the value of this Quadruple.
   * The value of the {@code long} subtrahend is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires a new value that equals the difference between the previous value and the value of the subtrahend.
   * @param subtrahend the value to be subtracted from the current value of this Quadruple
   * @return the reference to this object, which holds a new value that equals
   * the difference between its previous value and the value of the subtrahend
   */
  public ImmutableQuadruple subtract(long subtrahend) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.subtract(subtrahend);
    return newInstance;
  }

  /**
   * Subtracts the value of the given {@code double} subtrahend from the value of this Quadruple.
   * The value of the {@code double} subtrahend is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires a new value that equals the difference between the previous value and the value of the subtrahend.
   * @param subtrahend the value to be subtracted from the current value of this Quadruple
   * @return the reference to this object, which holds a new value that equals
   * the difference between its previous value and the value of the subtrahend
   */
  public ImmutableQuadruple subtract(double subtrahend) {
    final ImmutableQuadruple newInstance = new ImmutableQuadruple(this);
    newInstance.value.subtract(subtrahend);
    return newInstance;
  }

  /**
   * Subtracts the value of the {@code Quadruple} {@code subtrahend} from the value of the {@code minuend},
   * creates and returns a new  instance of Quadruple that contains the difference.
   * The operands remain unchanged.
   * @param minuend the value from which the subtrahend is to be subtracted
   * @param subtrahend the value to be subtracted from the minuend
   * @return a new instance of Quadruple containing the difference
   */
  public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, ImmutableQuadruple subtrahend) {
    minuend = new ImmutableQuadruple(minuend);
    minuend.value.subtract(subtrahend.value);
    return minuend;
  } // public static Quadruple subtract(Quadruple minuend, Quadruple subtrahend) {

  /**
   * Subtracts the value of the {@code long} {@code subtrahend} from the value of the {@code minuend},
   * creates and returns a new  instance of Quadruple that contains the difference.
   * The value of the {@code long} subtrahend is preliminarily converted to a {@code Quadruple} value.
   * The Quadruple minuend remains unchanged.
   * @param minuend the value from which the subtrahend is to be subtracted
   * @param subtrahend the value to be subtracted from the minuend
   * @return a new instance of Quadruple containing the difference
   */
  public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, long subtrahend) {
    minuend = new ImmutableQuadruple(minuend);
    minuend.value.subtract(subtrahend);
    return minuend;
  }

  /**
   * Subtracts the value of the {@code double} {@code subtrahend} from the value of the {@code minuend},
   * creates and returns a new  instance of Quadruple that contains the difference.
   * The value of the {@code double} subtrahend is preliminarily converted to a {@code Quadruple} value.
   * The Quadruple minuend remains unchanged.
   * @param minuend the value from which the subtrahend is to be subtracted
   * @param subtrahend the value to be subtracted from the minuend
   * @return a new instance of Quadruple containing the difference
   */
  public static ImmutableQuadruple subtract(ImmutableQuadruple minuend, double subtrahend) {
    minuend = new ImmutableQuadruple(minuend);
    minuend.value.subtract(subtrahend);
    return minuend;
  } // public static Quadruple subtract(Quadruple minuend, double subtrahend) {

  /**
   * Multiplies the value of this Quadruple by the value of the given {@code Quadruple} factor.
   * The instance acquires a new value that equals the product of the previous value and the value of the factor.
   * @param factor the value to multiply the current value of this Quadruple by.
   * @return the reference to this object, which holds a new value that equals
   * the product of its previous value and the value of the factor
   */
  public ImmutableQuadruple multiply(ImmutableQuadruple factor) {
    final ImmutableQuadruple product = new ImmutableQuadruple(this);
    product.value.multiply(factor.value);
    return product;
  } // public Quadruple multiply(Quadruple factor) {

  /**
   * Multiplies the value of this Quadruple by the value of the given {@code long} factor.
   * The value of the {@code long} factor is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires a new value that equals the product of the previous value and the value of the factor.
   * @param factor the value to multiply the current value of this Quadruple by.
   * @return the reference to this object, which holds a new value that equals
   * the product of its previous value and the value of the factor
   */
  public ImmutableQuadruple multiply(long factor) {
    final ImmutableQuadruple product = new ImmutableQuadruple(this);
    product.value.multiply(factor);
    return product;
  } // public Quadruple multiply(long factor) {

  /**
   * Multiplies the value of this Quadruple by the value of the given {@code double} factor.
   * The value of the {@code double} factor is preliminarily converted to a {@code Quadruple} value.
   * The instance acquires a new value that equals the product of the previous value and the value of the factor.
   * @param factor the value to multiply the current value of this Quadruple by.
   * @return the reference to this object, which holds a new value that equals
   * the product of its previous value and the value of the factor
   */
  public ImmutableQuadruple multiply(double factor) {
    final ImmutableQuadruple product = new ImmutableQuadruple(this);
    product.value.multiply(factor);
    return product;
  } // public Quadruple multiply(double factor) {

  /**
   * Multiplies the value of the given {@code Quadruple factor1} by the {@code Quadruple factor2},
   * creates and returns a new instance of Quadruple containing the product.
   * The operands remain unchanged.
   * @param factor1 the 1st factor to be multiplied by the second one
   * @param factor2 the 2nd factor to be multiplied by the first one
   * @return a new instance of Quadruple containing the value of the product
   */
  public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, ImmutableQuadruple factor2) {
    factor1 = new ImmutableQuadruple(factor1);
    factor1.value.multiply(factor2.value);
    return factor1;
  }

  /**
   * Multiplies the value of the given {@code Quadruple factor1} by the {@code long factor2},
   * creates and returns a new instance of Quadruple containing the product.
   * The value of the {@code long} factor is preliminarily converted to a {@code Quadruple} value.
   * The operands remain unchanged.
   * @param factor1 the 1st factor to be multiplied by the second one
   * @param factor2 the 2nd factor to be multiplied by the first one
   * @return a new instance of Quadruple containing the value of the product
   */
  public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, long factor2) {
    factor1 = new ImmutableQuadruple(factor1);
    factor1.value.multiply(factor2);
    return factor1;
  } // public static Quadruple multiply(Quadruple factor1, long factor2) {

  /**
   * Multiplies the value of the given {@code Quadruple factor1} by the {@code double factor2},
   * creates and returns a new instance of Quadruple containing the product.
   * The value of the {@code double} factor is preliminarily converted to a {@code Quadruple} value.
   * The operands remain unchanged.
   * @param factor1 the 1st factor to be multiplied by the second one
   * @param factor2 the 2nd factor to be multiplied by the first one
   * @return a new instance of Quadruple containing the value of the product
   */
  public static ImmutableQuadruple multiply(ImmutableQuadruple factor1, double factor2) {
    factor1 = new ImmutableQuadruple(factor1);
    factor1.value.multiply(factor2);
    return factor1;
  } // public static Quadruple multiply(Quadruple factor1, double factor2) {

  /**
   * Divides the value of this Quadruple by the value of the given {@code Quadruple} divisor.
   * The instance acquires a new value that equals the quotient.
   * @param divisor the divisor to divide the current value of this Quadruple by
   * @return the reference to this object, which holds a new value that equals
   * the quotient of the previous value of this Quadruple divided by the given divisor
   */
  public ImmutableQuadruple divide(ImmutableQuadruple divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(this);
    quotient.value.divide(divisor.value);
    return quotient;
  }

  /**
   * Divides the value of this Quadruple by the value of the given {@code long} divisor.
   * The instance acquires a new value that equals the quotient.
   * The value of the {@code long} divisor is preliminarily converted to a {@code Quadruple} value.
   * @param divisor the divisor to divide the current value of this Quadruple by
   * @return the reference to this object, which holds a new value that equals
   * the quotient of the previous value of this Quadruple divided by the given divisor
   */
  public ImmutableQuadruple divide(long divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(this);
    quotient.value.divide(divisor);
    return quotient;
  }

  /**
   * Divides the value of this Quadruple by the value of the given {@code double} divisor.
   * The instance acquires a new value that equals the quotient.
   * The value of the {@code double} divisor is preliminarily converted to a {@code Quadruple} value.
   * @param divisor the divisor to divide the current value of this Quadruple by
   * @return the reference to this object, which holds a new value that equals
   * the quotient of the previous value of this Quadruple divided by the given divisor
   */
  public ImmutableQuadruple divide(double divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(this);
    quotient.value.divide(divisor);
    return quotient;
  }

  /**
   * Divides the value of the given dividend by the value of the given {@code Quadruple} divisor,
   * creates and returns a new instance of Quadruple containing the quotient.
   * The operands remain unchanged.
   * @param dividend the value to be divided by the divisor
   * @param divisor the divisor to divide the dividend by
   * @return a new instance of Quadruple, which holds the value of the quotient
   */
  public static ImmutableQuadruple divide(ImmutableQuadruple dividend, ImmutableQuadruple divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(dividend);
    quotient.value.divide(divisor.value);
    return quotient;
  }

  /**
   * Divides the value of the given dividend by the value of the given {@code long} divisor,
   * creates and returns a new instance of Quadruple containing the quotient.
   * The value of the {@code long} divisor is preliminarily converted to a {@code Quadruple} value.
   * The operands remain unchanged.
   * @param dividend the value to be divided by the divisor
   * @param divisor the divisor to divide the dividend by
   * @return a new instance of Quadruple, which holds the value of the quotient
   */
  public static ImmutableQuadruple divide(ImmutableQuadruple dividend, long divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(dividend);
    quotient.value.divide(divisor);
    return quotient;
  }

  /**
   * Divides the value of the given dividend by the value of the given {@code double} divisor,
   * creates and returns a new instance of Quadruple containing the quotient.
   * The value of the {@code double} divisor is preliminarily converted to a {@code Quadruple} value.
   * The operands remain unchanged.
   * @param dividend the value to be divided by the divisor
   * @param divisor the divisor to divide the dividend by
   * @return a new instance of Quadruple, which holds the value of the quotient
   */
  public static ImmutableQuadruple divide(ImmutableQuadruple dividend, double divisor) {
    final ImmutableQuadruple quotient = new ImmutableQuadruple(dividend);
    quotient.value.divide(divisor);
    return quotient;
  }

  /* ***********************************************************************************
   ****** Square root ******************************************************************
   *********************************************************************************** */

  /**
   * Computes a square root of the value of this {@code Quadruple}
   * and replaces the old value of this instance with the newly-computed value.
   * @return the reference to this instance, which holds a new value that equals
   * to the square root of its previous value
   */
  public ImmutableQuadruple sqrt() {
    final ImmutableQuadruple result = new ImmutableQuadruple(this);
    result.value.sqrt();
    return result;
  }

  /**
   * Computes a square root of the value of the given {@code Quadruple},
   * creates and returns a new instance of Quadruple containing the value of the square root.
   * The parameter remains unchanged.
   * @param square the value to find the square root of
   * @return a new instance of Quadruple containing the value of the square root of the given argument
   */
  public static ImmutableQuadruple sqrt(Quadruple square) {
    final ImmutableQuadruple result = new ImmutableQuadruple(square);
    result.value.sqrt();
    return result;
  } // public static Quadruple sqrt(Quadruple square) {

  /* ***********************************************************************************
   ****** Miscellaneous utility methods ************************************************
   *********************************************************************************** */

  protected void ________Miscellaneous_utility_methods_________ () {} // Just to put a visible mark of the section in the outline view of the IDE

  /**
   * Changes the sign of this Quadruple.
   * @return the reference to this object, which holds a new value that
   * equals the previous value in magnitude, but with opposite sign
   */
  public ImmutableQuadruple negate() {
    final ImmutableQuadruple result = new ImmutableQuadruple(this);
    result.value.negate();
    return result;
  }

  /**
   * Returns a new instance of {@code Quadruple} with the value of the absolute value of this instance
   * @return a new instance of {@code Quadruple} with the value of the absolute value of this instance
   */
  public ImmutableQuadruple abs() {
    if (!value.isNegative()) {
      return this;
    }
    final ImmutableQuadruple result = new ImmutableQuadruple(this);
    result.value.negate();
    return result;
  }


  /**
   * Returns 1 for positive values, -1 for negative values (including -0), and 0 for the positive zero value
   * @return 1 for positive values, -1 for negative values (including -0), and 0 for the positive zero value
   */
  public int signum() {
    return  isNegative()? -1 :
            isZero()?      0 :
                           1;
  } // public int signum() {

  /**
   * Creates a new Quadruple instance with a pseudo-random value
   * using a static randomly initialized {@code java.util.Random} instance.
   * The generated value falls within the range 0.0 (inclusive) to 1.0 (exclusive).
   * @return a new instance containing a next random value
   */
  public static ImmutableQuadruple nextRandom() {
    return new ImmutableQuadruple(Quadruple.nextRandom());
  }

  /**
   * Creates a new Quadruple instance with a pseudo-random value
   * using the given {@code java.util.Random} instance.
   * The generated value falls within the range 0.0 (inclusive) to 1.0 (exclusive).
   * Can be used to repeatedly generate the same pseudo-random sequence.
   * @param rand an instance of {@code java.util.Random} to be used for generating the random value
   * @return a new instance containing the next random value
   */
  public static ImmutableQuadruple nextRandom(Random rand) {
    return new ImmutableQuadruple(Quadruple.nextRandom(rand));
  }

}
