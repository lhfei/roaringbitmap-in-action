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
import java.nio.ByteBuffer;
import java.util.Base64;

import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class SerializeToStringExample {

  // This example uses the Base64 class introduced in Java 8. Any byte[] to String
  // encoder would do
  public static void main(String[] args) throws IOException {
    MutableRoaringBitmap mrb = MutableRoaringBitmap.bitmapOf(1, 2, 3, 1000);
    System.out.println("starting with  bitmap " + mrb);
    ByteBuffer outbb = ByteBuffer.allocate(mrb.serializedSizeInBytes());
    // If there were runs of consecutive values, you could
    // call mrb.runOptimize(); to improve compression
    mrb.serialize(outbb);
    //
    outbb.flip();
    String serializedstring = Base64.getEncoder().encodeToString(outbb.array());
    ByteBuffer newbb = ByteBuffer.wrap(Base64.getDecoder().decode(serializedstring));
    ImmutableRoaringBitmap irb = new ImmutableRoaringBitmap(newbb);
    System.out.println("read bitmap " + irb);
  }
}
