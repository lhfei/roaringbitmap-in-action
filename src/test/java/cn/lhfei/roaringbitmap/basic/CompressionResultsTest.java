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

import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Feb 21, 2019
 */

public class CompressionResultsTest extends BasicTestSuite {

  public static final int universe_size = 262144;
  public static DecimalFormat F = new DecimalFormat("0.000");

  public void testSuperSparse() {
    LOG.info("Sparse case... universe = [0, {})", universe_size);
    RoaringBitmap r = new RoaringBitmap();
    int howmany = 100;
    int gap = universe_size / howmany;
    LOG.info("Adding {} values separated by gaps of {} ...", howmany, gap);
    LOG.info("As a bitmap it would look like 1000...001000... ");
    for (int i = 1; i < howmany; i++) {
      r.add(i * gap);
    }
    LOG.info("Bits used per value = ", F.format(r.getSizeInBytes() * 8.0 / howmany));
    r.runOptimize();
    LOG.info("Bits used per value after run optimize = {}",
        F.format(r.getSizeInBytes() * 8.0 / howmany));
    LOG.info("An uncompressed bitset might use {} bits per value set {}",
        F.format(r.getSizeInBytes() * 8.0 / howmany));
  }

  public void testSuperDense() {
    LOG.info("Sparse case... universe = [0, {})", universe_size);
    RoaringBitmap r = new RoaringBitmap();
    int howmany = 100;
    int gap = universe_size / howmany;
    for (int i = 1; i < howmany; i++) {
      r.add(i * gap + 1, ((i + 1) * gap));
    }
    LOG.info("Adding {} values partionned by {} gaps of 1 ...", r.getCardinality(), howmany);
    LOG.info("As a bitmap it would look like 01111...11011111... ");

    LOG.info("Bits used per value = {}", F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    r.runOptimize();
    LOG.info("Bits used per value after run optimize = {}",
        F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    LOG.info("Bits used per gap after run optimize = {}",
        F.format(r.getSizeInBytes() * 8.0 / howmany));

    LOG.info("An uncompressed bitset might use {}",
        F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
  }

  public void testAlternating() {
    LOG.info("Alternating case... universe = [0, {})", universe_size);
    RoaringBitmap r = new RoaringBitmap();
    for (int i = 1; i < universe_size; i++) {
      if (i % 2 == 0)
        r.add(i);
    }
    LOG.info("Adding all even values in the universe");
    LOG.info("As a bitmap it would look like 01010101... ");
    LOG.info("Bits used per value = {}", F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    r.runOptimize();
    LOG.info("Bits used per value after run optimize = {}",
        F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
    LOG.info("An uncompressed bitset might use {}",
        F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
  }

  @Test
  public void compresed() {
    testSuperSparse();
    testSuperDense();
    testAlternating();
  }
}
