// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.drx.epi;

import java.util.Arrays;
import java.util.Random;

public class Replace_and_remove {
	// @include
	static String replace_and_remove(String s) {
		char[] s_chars = s.toCharArray();
	  // Removes "b" and count the number of "a".
	  int write_idx = 0, a_count = 0;
	  for (char c : s_chars) {
	    if (c != 'b') {
	      s_chars[write_idx++] = c;
	    }
	    if (c == 'a') {
	      ++a_count;
	    }
	  }

	  // Allocates space according to the number of "a".
	  s_chars = Arrays.copyOf(s_chars, write_idx + a_count);
	  // Replace "a" with "dd".
	  int cur_idx = write_idx - 1;
	  write_idx = s_chars.length - 1;
	  while (cur_idx >= 0) {
	    if (s_chars[cur_idx] == 'a') {
	      s_chars[write_idx--] = 'd';
	      s_chars[write_idx--] = 'd';
	    } else {
	      s_chars[write_idx--] = s_chars[cur_idx];
	    }
	    --cur_idx;
	  }
	  return new String(s_chars);
	}

	// @exclude

	static String rand_string(int len) {
		Random gen = new Random();
		StringBuilder ret = new StringBuilder();
		while (len-- > 0) {
			ret.append((char) (gen.nextInt(4) + 97));
		}

		return ret.toString();
	}

	static void check_ans(String s, String ans) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == 'a') {
				temp.append('d');
				temp.append('d');
			} else if (s.charAt(i) != 'b') {
				temp.append(s.charAt(i));
			}
		}
		assert (ans.equals(temp.toString()));
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 1000; ++times) {
			String s;
			if (args.length == 1) {
				s = args[0];
			} else {
				s = rand_string(gen.nextInt(1000) + 1);
			}
			System.out.println(s);
			System.out.println();
			String ans = replace_and_remove(s);
			System.out.println(ans);
			check_ans(s, ans);
		}
	}
}
