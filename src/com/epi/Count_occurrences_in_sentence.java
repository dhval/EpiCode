// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.util.Random;
import java.util.Arrays;

// @include
class Count_occurrences_in_sentence {
  public static void count_occurrences(String S) {
    char[] A = S.toCharArray();
    Arrays.sort(A);

    int count = 1;
    for (int i = 1; i < A.length; ++i) {
      if (A[i] == A[i - 1]) {
        ++count;
      } else {
        System.out.print("(" + A[i - 1] + "," + count + "),");
        count = 1;
      }
    }
    System.out.println("(" + A[A.length - 1] + ',' + count + ")");
  }
// @exclude

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
    String S = null;
    if (args.length == 1) {
      S = args[0];
    } else {
      S = rand_string(rnd.nextInt(1000) + 1);
    }
    System.out.println(S);
    count_occurrences(S);
  }
// @include
}
