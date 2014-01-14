// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class three_sum {
	
  // @include
  public static boolean has_2_sum(ArrayList<Integer> A, int t) {
    int j = 0, k = A.size() - 1;
  
    while (j <= k) {
      if (A.get(j) + A.get(k) == t) {
        return true;
      } else if (A.get(j) + A.get(k) < t) {
        ++j;
      } else {  // A[j] + A[k] > t.
        --k;
      }
    }
    return false;
  }

  public static boolean has_3_sum(ArrayList<Integer> A, int t) {
    Collections.sort(A);  
    for (Integer a: A) {
      // Find if the sum of two numbers in A equals to t - a.
      if (has_2_sum(A, t - a)) {
        return true;
      }
    }
    return false;
  }
  //@exclude

  // n^3 solution
  public static boolean check_ans(ArrayList<Integer> A, int t) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.size(); ++j) {
        for (int k = 0; k < A.size(); ++k) {
          if (A.get(i) + A.get(j) + A.get(k) == t) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] argv) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, T;
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);        
        T = gen.nextInt(n - 1);
      } else {        
        n = gen.nextInt(10000) + 1;        
        T = gen.nextInt(n-1);
      }
      
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {               
        A.add(gen.nextInt(200000) - 100000);
      }
      System.out.println(has_3_sum(A, T)?"true":"false");      
      assert(check_ans(A, T) == has_3_sum(A, T));
    }    
  }
}
