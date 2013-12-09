// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.drx.epi.utils.Utils.*;

public class Next_permutation {
	// @include
	static List<Integer> next_permutation(List<Integer> p) {
	  int k = p.size() - 2;
	  while (k >= 0 && p.get(k) >= p.get(k + 1)) {
	    --k;
	  }
	  if (k == -1) {
	    return Collections.emptyList();  // p is the last permutation.
	  }

	  int l = 0;
	  for (int i = k + 1; i < p.size(); ++i) {
	    if (p.get(i) > p.get(k)) {
	      l = i;
	    } else {
	      break;
	    }
	  }
	  swap(p, k, l);

	  // Produce the lexicographically minimal permutation.
	  Collections.reverse(p.subList(k + 1, p.size()));
	  return p;
	}
	// @exclude

	public static void main(String[] args) {
		Random gen = new Random();

		for (int times = 0; times < 1000; ++times) {
			List<Integer> p = new ArrayList<Integer>();
			if (args.length > 2) {
				for (int i = 1; i < args.length; ++i) {
					p.add(Integer.valueOf(args[i]));
				}
			} else {
				int n = (args.length == 2 ? Integer.valueOf(args[1]) : (gen.nextInt(100) + 1));
				for (int i = 0; i < n; ++i) {
					p.add(gen.nextInt(n));
				}
			}

			List<Integer> ans = new ArrayList<Integer>(next_permutation(p));
			next_permutation(p);
			assert equal(ans, p);
		}

	}
}
