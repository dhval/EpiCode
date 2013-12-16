package com.drx.epi;

import java.util.List;
import java.util.Random;

import static com.drx.epi.utils.Utils.simplePrint;

public class Reservoir_sampling {
	// @include
	List<Integer> reservoir_sampling(String sin, int k) {
	  int x;
	  List<Integer> R;
	  // Store the first k elements.
	  String[] xs = sin.split("\\s");
	  for (int i = 0; i < k && *sin >> x; ++i) {
	    R.add(Integer.valueOf(xs[i]));
	  }

	  // After the first k elements.
	  int element_num = k + 1;
	  while (*sin >> x) {
	    Random gen = new Random();  // random num generator.
	    // Generate random int in [0, element_num].
	    int tar = gen.nextInt(element_num + 1);
	    element_num++;
	    if (tar < k) {
	      R[tar] = x;
	    }
	  }
	  return R;
	}
	// @exclude

	public static void main(String[] args) {
	  int n, k;
	  Random gen = new Random();
	  if (args.length == 1) {
	    n = Integer.valueOf(args[0]);
	    k = gen.nextInt(n) + 1;
	  } else if (args.length == 2) {
		  n = Integer.valueOf(args[0]);
		  k = Integer.valueOf(args[1]);
	  } else {
	    n = gen.nextInt(100000);
	    k = gen.nextInt(n) + 1;
	  }
	  
	  int[] A = new int[n];
	  for (int i = 0; i < n; ++i) {
	    A[i] = i;
	  }
	  StringBuilder s = new StringBuilder();
	  for (int i = 0; i < A.length; ++i) {
	    s.append(A[i]);
	    s.append(' ');
	  }
	  System.out.println(n + " " + k);
	  istringstream sin(s);
	  List<Integer> ans = reservoir_sampling(sin, k);
	  assert ans.size() == k;
	  
	}
}
