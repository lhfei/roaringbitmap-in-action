/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.lhfei.roaringbitmap.basic;

import java.text.DecimalFormat;

import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class CompressionResults {
  public static final int universe_size = 262144;
  public static DecimalFormat F = new DecimalFormat("0.000");

  public static void testSuperSparse() {
    System.out.println("Sparse case... universe = [0," + universe_size + ")");
    RoaringBitmap r = new RoaringBitmap();
    int howmany = 100;
    int gap = universe_size / howmany;
    System.out.println("Adding " + howmany + " values separated by gaps of " + gap + "...");
    System.out.println("As a bitmap it would look like 1000...001000... ");
    for (int i = 1; i < howmany; i++) {
      r.add(i * gap);
    }
    System.out.println("Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / howmany));
    r.runOptimize();
    System.out.println(
        "Bits used per value after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / howmany));
    System.out.println("An uncompressed bitset might use " + F.format(universe_size * 1.0 / howmany)
        + " bits per value set");
    System.out.println();

  }

  public static void testSuperDense() {
    System.out.println("Sparse case... universe = [0," + universe_size + ")");
    RoaringBitmap r = new RoaringBitmap();
    int howmany = 100;
    int gap = universe_size / howmany;
    for (int i = 1; i < howmany; i++) {
      r.add(new Long(i * gap + 1), new Long(((i + 1) * gap)));
    }
    System.out.println(
        "Adding " + r.getCardinality() + " values partionned by " + howmany + " gaps of 1 ...");
    System.out.println("As a bitmap it would look like 01111...11011111... ");

    System.out.println(
        "Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    r.runOptimize();
    System.out.println("Bits used per value after run optimize = "
        + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    System.out.println(
        "Bits used per gap after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / howmany));

    System.out.println("An uncompressed bitset might use "
        + F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
    System.out.println();
  }

  public static void testAlternating() {
    System.out.println("Alternating case... universe = [0," + universe_size + ")");
    RoaringBitmap r = new RoaringBitmap();
    for (int i = 1; i < universe_size; i++) {
      if (i % 2 == 0)
        r.add(i);
    }
    System.out.println("Adding all even values in the universe");
    System.out.println("As a bitmap it would look like 01010101... ");
    System.out.println(
        "Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    r.runOptimize();
    System.out.println("Bits used per value after run optimize = "
        + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    System.out.println("An uncompressed bitset might use "
        + F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
    System.out.println();
  }

  public static void main(String[] args) {
    testSuperSparse();
    testSuperDense();
    testAlternating();
  }
}
