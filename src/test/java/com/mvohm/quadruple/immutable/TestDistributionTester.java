package com.mvohm.quadruple.immutable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import static com.mvohm.quadruple.immutable.test.AuxMethods.*;

  public class TestDistributionTester {
    public static double analyzeDistribution(int[] values) {
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
      final double expected = (double) size / bucketCount;
      double variance = 0;
      for (final int count : buckets) {
        variance += Math.pow((count - expected)/expected, 2);
      }
      variance /= bucketCount;
      final double stddev = Math.sqrt(variance);
      double uniformity = 1.0 / (1.0 + stddev); // Normalize
      if (uniformity == 1.0) {
        uniformity = 1 - 1e-15;     // so as not to confuse with duplicates
      }
      return duplicates + uniformity;
    }

    private static final int SIZE = 1_000;
    private static final int RANGE = 1_000_000;

    public static void main(String[] args) {
      final int[] values = new int[SIZE];
      final Random r = new Random();
      for (int i = 0; i < values.length; i++) {
        values[i] = r.nextInt(0, RANGE);
//        values[i] = i;    // Should return 100% uniformity
      }

//      for (int i = 0; i < 6; i++) { // Add duplicates
//        values[i] = 25;
//      }

      final double result = analyzeDistribution(values);
      final int duplicates = (int)result;
      final double uniformity = result - duplicates;
      say("Uniformity: %.3f", uniformity);
      say("Duplicates: %s", duplicates);
    }
}


