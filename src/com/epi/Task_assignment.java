// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class pair{
  int first, second;
  pair(int f, int s){
    first = f;
    second = s;
  }
}

class Task_assignment{
  // @include
  public static ArrayList<pair> task_assignment(ArrayList<Integer> A) {
    Collections.sort(A);
    ArrayList<pair> P = new ArrayList<pair>();
    for (int i = 0, j = A.size() - 1; i < j; ++i, --j) {
      P.add(new pair(A.get(i), A.get(j)));
    }
    return P;
  }
  // @exclude
  
  public static void main(String[] argv) {
    Random gen = new Random();
    int n;
    if (argv.length == 1) {
      n = Integer.parseInt(argv[0]);
    } else {      
      n = gen.nextInt(10000) + 1;
    }
    ArrayList<Integer> A = new ArrayList<Integer>();
    for (int i = 0; i < n; ++i) {      
      A.add(gen.nextInt(999));
    }
    ArrayList<pair> P = task_assignment(A);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < P.size(); ++i) {
      if (P.get(i).first + P.get(i).second > max) {
        max = P.get(i).first + P.get(i).second;
      }
    }
    System.out.println(max);    
  }
}