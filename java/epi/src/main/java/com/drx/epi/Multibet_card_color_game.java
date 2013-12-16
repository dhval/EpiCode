package com.drx.epi;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.pow;;

public class Multibet_card_color_game {
	// @include
	static double compute_best_payoff_helper(
	    Map<Integer, Map<Integer, Map<Integer, Double>>> cache,
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

	  boolean best_exists = cache.get(cash) != null 
			  && cache.get(cash).get(num_red) != null 
			  && cache.get(cash).get(num_red).get(num_cards) != null;
	  
	  if (!best_exists) {
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
	    
	    if (cache.get(cash) == null) {
	    	cache.put(cash, new HashMap<Integer, Map<Integer, Double>>());
	    }
	    
	    if (cache.get(cash).get(num_red) == null) {
	    	cache.get(cash).put(num_red, new HashMap<Integer, Double>());
	    }
	    
	    cache.get(cash).get(num_red).put(num_cards, best);
	  }
	  return cache.get(cash).get(num_red).get(num_cards);
	}

	static double compute_best_payoff(int cash) {
		double upper_bound = 9.09 * cash;
		Map<Integer, Map<Integer, Map<Integer, Double>>> cache = new HashMap<Integer, Map<Integer, Map<Integer, Double>>>();
		return compute_best_payoff_helper(cache, upper_bound, cash, 26, 52);
	}
	// @exclude

	public static void main(String[] args) {
		  int ans = (int) compute_best_payoff(100);
		  assert(ans == 808);
		  System.out.println("100 cash can get " + ans);
	}
}
