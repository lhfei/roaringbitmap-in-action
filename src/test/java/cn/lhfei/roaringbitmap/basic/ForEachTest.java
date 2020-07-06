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

import java.io.IOException;

import org.junit.Test;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Feb 21, 2019
 */
public class ForEachTest extends BasicTestSuite {

  @Test
  public void feach() throws IOException {

    RoaringBitmap rb = new RoaringBitmap();
    for (int k = 0; k < 100000; k += 1000) {
      rb.add(k);
    }
    for (int k = 100000; k < 200000; ++k) {
      rb.add(3 * k);
    }
    for (int k = 700000; k < 800000; ++k) {
      rb.add(k);
    }
    final int[] count = {0};
    rb.forEach(new IntConsumer() {
      @Override
      public void accept(int value) {
        if ((value % 1500) == 0) {
          count[0]++;
        }
      }
    });

    LOG.info("There are {} values divisible by 1500.", count[0]);
  }
}
