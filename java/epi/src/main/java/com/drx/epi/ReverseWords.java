// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import static com.drx.epi.utils.Utils.find;
import static com.drx.epi.utils.Utils.reverse;

import java.util.Random;

public class ReverseWords {
	static String rand_string(int len) {
		Random gen = new Random();
		StringBuilder ret = new StringBuilder();
		while (len-- > 0) {
			int ch = gen.nextInt(53);
			if (ch == 52) {
				ret.append(' ');
			} else if (ch < 26) {
				ret.append(ch + 'a');
			} else {
				ret.append(ch - 26 + 'A');
			}
		}
		return ret.toString();
	}

		// @include
		static String reverse_words(String input) {
			// Reverse the whole string first.
			char[] reversed = input.toCharArray();
			reverse(reversed, 0, input.length());

			  int start = 0, end;
			  while ((end = find(reversed, ' ', start) ) != -1) {
			    // Reverse each word in the string.
			    reverse(reversed, start, end);
			    start = end + 1;
			  }
			  // Reverse the last word.
			  reverse(reversed, start, reversed.length);
			  
			  return new String(reversed);
		}
		// @exclude

		static void check_answer(String ori, String str) {
		  String reversed = reverse_words(str);
		  assert ori.equals(reversed);
		}

		public static void main(String[] args) {
			Random gen = new Random();
			  for (int times = 0; times < 1000; ++times) {
			    String str = "";
			    if (args.length >= 1) {
			      str += args[0];
			      for (int i = 1; i < args.length; ++i) {
			        str += ' ';
			        str += args[i];
			      }
			    } else {
			      str = rand_string(gen.nextInt(10000));
			    }
			    System.out.println(str);
			    String reversed = reverse_words(str);
			    System.out.println(reversed);
			    check_answer(str, reversed);
			  }
		}
}
