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

import org.junit.jupiter.api.Test;
import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Feb 02, 2021
 */
public class VeryLargeBitmapTest extends BasicTestSuite {

  @Test
  public void buildLargeBitmap() {
    RoaringBitmap rb = new RoaringBitmap();
    rb.add(0L, 1L << 32);// the biggest bitmap we can create

    LOG.info("Total Memfory Size : {}", rb.getLongSizeInBytes());
    LOG.info("memory usage: {} byte per value", rb.getSizeInBytes() * 1.0 / (1L << 32));
    if (rb.getLongCardinality() != (1L << 32))
      throw new RuntimeException("bug!");
  }
}
