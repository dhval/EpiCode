package com.drx.epi;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;

public class MultibetCardColorGame {
	// @include
	static double compute_best_payoff_helper(
		Double[][][] cache,
	    double upper_bound,
	    int cash,
	    int num_red,
	    int num_cards) {
		
	  if (cash >= upper_bound) {
	    return cash;
	  }

	  if (num_red == num_cards || num_red == 0) {
	    return cash * pow(2, num_cards);
	  }

	  if (cache[cash][num_red][num_cards] == null) {
	    double best = Double.MIN_VALUE;
	    for (int bet = 0; bet <= cash; ++bet) {
	      double red_lower_bound =
	          min(compute_best_payoff_helper(
	                  cache, upper_bound, cash + bet, num_red - 1, num_cards - 1),
	              compute_best_payoff_helper(
	                  cache, upper_bound, cash - bet, num_red, num_cards - 1));

	      double black_lower_bound =
	          min(compute_best_payoff_helper(
	                  cache, upper_bound, cash - bet, num_red - 1, num_cards - 1),
	              compute_best_payoff_helper(
	                  cache, upper_bound, cash + bet, num_red, num_cards - 1));
	      best = max(best, max(red_lower_bound, black_lower_bound));
	    }
	    
	    cache[cash][num_red][num_cards] = best;
	  }
	  
	  return cache[cash][num_red][num_cards];
	}

	static double compute_best_payoff(int cash) {
		double upper_bound = 9.09 * cash;
		int num_red = 26;
		int num_cards = 52;
		Double[][][] cache = new Double[(int) upper_bound][num_red + 1][num_cards + 1];
		return compute_best_payoff_helper(cache, upper_bound, cash, 26, 52);
	}
	// @exclude

	public static void main(String[] args) {
		  int ans = (int) compute_best_payoff(100);
		  assert ans == 808 ;
		  System.out.println("100 cash can get " + ans);
	}
}
