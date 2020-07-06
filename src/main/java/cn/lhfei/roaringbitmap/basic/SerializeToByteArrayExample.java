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

import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class SerializeToByteArrayExample {

  public static void main(String[] args) {
    RoaringBitmap mrb = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
    System.out.println("starting with  bitmap " + mrb);
    mrb.runOptimize(); // to improve compression
    byte[] array = new byte[mrb.serializedSizeInBytes()];
    mrb.serialize(ByteBuffer.wrap(array));
    RoaringBitmap ret = new RoaringBitmap();
    try {
      ret.deserialize(ByteBuffer.wrap(array));
    } catch (IOException ioe) {
      ioe.printStackTrace(); // should not happen
    }
    if (!ret.equals(mrb))
      throw new RuntimeException("bug");
    System.out.println("decoded from byte array : " + ret);

  }
}
