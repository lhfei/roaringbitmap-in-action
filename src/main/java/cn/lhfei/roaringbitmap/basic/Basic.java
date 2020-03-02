/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.roaringbitmap.basic;

import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class Basic {

	public static void main(String[] args) {
		RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
		RoaringBitmap rr2 = new RoaringBitmap();
		rr2.add(4000L, 4255L);

		RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
		rr.or(rr2); // in-place computation
		boolean equals = rror.equals(rr);// true
		if (!equals)
			throw new RuntimeException("bug");
		// number of values stored?
		long cardinality = rr.getLongCardinality();
		System.out.println(cardinality);
		// a "forEach" is faster than this loop, but a loop is possible:
		for (int i : rr) {
			System.out.println(i);
		}
	}
}
