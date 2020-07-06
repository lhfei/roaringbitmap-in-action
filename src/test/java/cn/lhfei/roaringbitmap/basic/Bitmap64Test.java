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

import org.junit.Test;
import org.roaringbitmap.longlong.LongBitmapDataProvider;
import org.roaringbitmap.longlong.LongIterator;
import org.roaringbitmap.longlong.Roaring64NavigableMap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Feb 21, 2019
 */

public class Bitmap64Test extends BasicTestSuite {

  @Test
  public void bitmap64() {
    LongBitmapDataProvider r = Roaring64NavigableMap.bitmapOf(1, 2, 100, 1000);
    r.addLong(1234);

    LOG.info("{}", r.contains(1)); // true
    LOG.info("{}", r.contains(3)); // false

    LongIterator i = r.getLongIterator();

    while (i.hasNext())
      LOG.info("{}", i.next());
  }
}
