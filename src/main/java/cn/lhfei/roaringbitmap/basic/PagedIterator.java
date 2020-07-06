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

import org.roaringbitmap.IntIterator;
import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class PagedIterator {

  public static void main(String[] args) {
    RoaringBitmap rr = new RoaringBitmap();
    for (int k = 0; k < 100; k++) {
      rr.add(k * 4 + 31);
    }

    IntIterator i = rr.getIntIterator();
    final int pageSize = 10;
    while (i.hasNext()) {
      // we print a page
      for (int k = 0; (k < pageSize) && i.hasNext(); k++) {
        System.out.print(i.next() + " ");
      }
      System.out.println();
    }
  }
}
