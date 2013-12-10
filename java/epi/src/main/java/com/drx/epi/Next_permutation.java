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

  // AA begin
  // derived from http://codeforces.com/blog/entry/3980

  static void printList( List<Integer> p ) {
    if ( p == null ) {
      System.out.println("null");
    } else {
      for ( int i : p ) {
        System.out.print(i + " " );
      } 
      System.out.println();
    }
  }


  private static List<Integer> goldenNextPermutation( final ArrayList<Integer> c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
    List<Integer> result = (List<Integer>) c.clone();
		int first = getFirst( result );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.size() - 1;
		while ( c.get(first).compareTo( c.get(toSwap) ) >= 0 )
			--toSwap;
		// 3. swap elements with indexes first and last
		myswap( result, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.size() - 1;
		while ( first < toSwap )
			myswap( result, first++, toSwap-- );
		return result;
	}
  
  // finds the largest k, that c[k] < c[k+1]
	// if no such k exists (there is not greater permutation), return -1
	private static int getFirst( final List<Integer> c ) {
		for ( int i = c.size() - 2; i >= 0; --i )
			if ( c.get(i).compareTo( c.get(i + 1 ) ) < 0 )
				return i;
		return -1;
	}

	// swaps two elements (with indexes i and j) in array 
	private static void myswap( List<Integer> c, final int i, final int j ) {
		final Integer tmp = c.get(i);
		c.set(i, c.get(j) );
		c.set(j, tmp );
	}
  // AA end

	public static void main(String[] args) {

		Random gen = new Random();

		for (int times = 0; times < 1000; ++times) {
			ArrayList<Integer> p = new ArrayList<Integer>();
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

      // goldenNextPermutation does not change does not change p
			List<Integer> gold = goldenNextPermutation( p );

      // System.out.println("gold = ");
      // printList(gold);
      // System.out.println("original p = ");
      // printList(p);
      // p might be null, so cannot just ignore return value

			List<Integer> tmp = next_permutation(p);
      if ( tmp.equals( Collections.emptyList() ) ) {
        p = null;
      } else {
        p = (ArrayList<Integer>) tmp;
      }

      // System.out.println("new p = ");
      // printList(p);

			assert ( gold == null && p == null || gold.equals(p));
		}
	}
}
