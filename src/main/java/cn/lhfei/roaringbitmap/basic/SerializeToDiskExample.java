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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.roaringbitmap.RoaringBitmap;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 02, 2020
 */
public class SerializeToDiskExample {

  public static void main(String[] args) throws IOException {
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
    DataOutputStream out;
    String file1 = "bitmapwithoutruns.bin";
    out = new DataOutputStream(new FileOutputStream(file1));
    rb.serialize(out);
    out.close();
    rb.runOptimize();
    String file2 = "bitmapwithruns.bin";
    out = new DataOutputStream(new FileOutputStream(file2));
    rb.serialize(out);
    out.close();
    // verify:
    DataInputStream in;
    in = new DataInputStream(new FileInputStream(file1));
    RoaringBitmap rbtest = new RoaringBitmap();
    rbtest.deserialize(in);
    if (!rbtest.equals(rb))
      throw new RuntimeException("bug!");
    in = new DataInputStream(new FileInputStream(file2));
    rbtest.deserialize(in);
    if (!rbtest.equals(rb))
      throw new RuntimeException("bug!");
    System.out.println("Serialized bitmaps to " + file1 + " and " + file2);
  }
}
