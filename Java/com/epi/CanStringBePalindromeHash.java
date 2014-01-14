// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Map;
import java.util.HashMap;

class Can_string_be_a_palindrome_hash {
  // @include
  public static boolean can_string_be_a_palindrome_hash(String s) {
    HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
    // Insert each char into hash.
    for (char c : s.toCharArray()) {
      if (!hash.containsKey(c))
        hash.put(c, 1);
      else
        hash.put(c, hash.get(c) + 1);
    }

    // A string can be permuted as a palindrome if the number of odd time
    // chars <= 1.
    int odd_count = 0;
    for (Map.Entry<Character, Integer> p : hash.entrySet()) {
      if ((p.getValue() & 1) != 0 && ++odd_count > 1) {
        break;
      }
    }
    return odd_count <= 1;
  }
  // @exclude
}
