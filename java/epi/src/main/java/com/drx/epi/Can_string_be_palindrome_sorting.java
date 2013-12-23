// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.drx.epi;

import java.util.Arrays;

class Can_string_be_a_palindrome_sorting { 
  // @include
  public static boolean can_string_be_a_palindrome_sorting(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    int odd_count = 0;
    int num_curr_char = 1;

    for (int i = 1; i < a.length && odd_count <= 1; ++i) {
      if (a[i] != a[i - 1]) {
        if ((num_curr_char & 1) != 0) {
          ++odd_count;
        }
        num_curr_char = 1;
      } else {
        ++num_curr_char;
      }
    }
    if ((num_curr_char & 1) != 0) {
      ++odd_count;
    }

    // A string can be permuted as a palindrome if the number of odd time
    // chars <= 1.
    return odd_count <= 1;
  }
  // @exclude
}
