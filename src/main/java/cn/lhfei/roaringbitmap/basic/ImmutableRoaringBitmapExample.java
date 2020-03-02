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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class ImmutableRoaringBitmapExample {
	public static void main(String[] args) throws IOException {
		MutableRoaringBitmap rr1 = MutableRoaringBitmap.bitmapOf(1, 2, 3, 1000);
		MutableRoaringBitmap rr2 = MutableRoaringBitmap.bitmapOf(2, 3, 1010);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		// If there were runs of consecutive values, you could
		// call rr1.runOptimize(); or rr2.runOptimize(); to improve compression
		rr1.serialize(dos);
		rr2.serialize(dos);
		dos.close();
		ByteBuffer bb = ByteBuffer.wrap(bos.toByteArray());
		ImmutableRoaringBitmap rrback1 = new ImmutableRoaringBitmap(bb);
		bb.position(bb.position() + rrback1.serializedSizeInBytes());
		ImmutableRoaringBitmap rrback2 = new ImmutableRoaringBitmap(bb);
		System.out.println(rrback1);
		System.out.println(rrback2);
	}
}
