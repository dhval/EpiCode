package com.drx.epi;

import static com.drx.epi.utils.Utils.iota;
import static com.drx.epi.utils.Utils.partial_sum;
import static com.drx.epi.utils.Utils.simplePrint;
import static com.drx.epi.utils.Utils.upper_bound;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.drx.epi.utils.BinaryOperators;
import com.drx.epi.utils.Utils;

public class Nonuniform_random_number_generation {
	// @include
	static double nonuniform_random_number_generation(List<Double> T, List<Double> P) {
	  List<Double> prefix_P = new ArrayList<Double>();
	  Utils.fill(prefix_P, P.size() + 1, 0D);
	  ListIterator<Double> iter = prefix_P.listIterator(); iter.next();
	  partial_sum(P.iterator(), iter, BinaryOperators.ADD);
	  // gen object is used to generate random numbers from in [0.0, 1.0]
	  Random gen = new Random();
	  
	  // upper_bound returns an iterator pointing to the first element in
	  // (prefix_P.cbegin(),prefix_P.cend()) which compares greater than gen.nextDouble()
	  // which is a uniform random number in [0.0,1.0].
	  int it = upper_bound(prefix_P, gen.nextDouble());
	  return T.get(it - 1);
	}
	// @exclude

	public static void main(String[] args) {
		Random gen = new Random();
		  int n;
		  if (args.length == 1) {
		    n = Integer.valueOf(args[0]);
		  } else {
		    n = gen.nextInt(50) + 1;
		  }
		  List<Double> T = new ArrayList<Double>(n);
		  iota(T, n, 0);
		  List<Double> P = new ArrayList<Double>();
		  double full_prob = 1.0;
		  for (int i = 0; i < n - 1; ++i) {
		    double pi = gen.nextDouble() * full_prob;
		    P.add(pi);
		    full_prob -= pi;
		  }
		  P.add(full_prob);
		  
		  simplePrint(T);
		  System.out.println();
		  
		  simplePrint(P);
		  System.out.println();
		  
		  System.out.println(nonuniform_random_number_generation(T, P));
		  // Test. Perform the nonuniform random number generation for n * kTimes times
		  // and calculate the distrbution of each bucket.
		  int kTimes = 100000;
		  int[] counts = new int[n];
		  for (int i = 0; i < n * kTimes; ++i) {
		    double t = nonuniform_random_number_generation(T, P);
		    ++counts[(int) t];
		  }
		  for (int i = 0; i < n; ++i) {
		    System.out.println((double) (counts[i]) / (n * kTimes) + " " + P.get(i));
		    assert Math.abs(((double) counts[i]) / (n * kTimes) - P.get(i)) < 0.01;
		  }
	}

}
