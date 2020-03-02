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
public class IntervalCheck {

	public static void main(String[] args) {
		// some bitmap
		RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);

		// we want to check if it intersects a given range [10,1000]
		int low = 10;
		int high = 1000;
		RoaringBitmap range = new RoaringBitmap();
		range.add((long) low, (long) high + 1);
		//
		//

		System.out.println(RoaringBitmap.intersects(rr, range)); // prints true if they intersect
	}
}