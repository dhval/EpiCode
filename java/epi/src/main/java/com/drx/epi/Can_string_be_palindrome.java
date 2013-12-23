// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.drx.epi;

import java.util.Random;

class Can_string_be_a_palindrome {
  private static String rand_string(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      String s = null;
      if (args.length == 1) {
        s = args[0];
      } else {
        s = rand_string(rnd.nextInt(10) + 1);
      }
      System.out.println(s);
      assert(Can_string_be_a_palindrome_hash.can_string_be_a_palindrome_hash(s) ==
             Can_string_be_a_palindrome_sorting.can_string_be_a_palindrome_sorting(s));
    }
  }
}
