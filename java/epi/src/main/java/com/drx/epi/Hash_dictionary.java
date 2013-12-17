// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.drx.epi;

import java.util.Random;

class Hash_dictionary {
  private static String rand_string(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static int string_hash(String str, int modulus) {
    int kMult = 997;
    int val = 0;
    for (char c : str.toCharArray()) {
      val = (val * kMult + c) % modulus;
    }
    return val;
  }
  // @exclude

  public static void main(String[] args) {
    String str = null;
    if (args.length == 1) {
      str = args[0];
    } else {
      Random rnd = new Random();
      str = rand_string(rnd.nextInt(20) + 1);
    }
    System.out.println("string = " + str);
    System.out.println(string_hash(str, 1 << 16));
  }
}
