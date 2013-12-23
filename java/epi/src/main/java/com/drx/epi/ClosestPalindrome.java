package com.drx.epi;

import static com.drx.epi.utils.Utils.fill;
import static java.lang.Math.abs;

import java.util.Random;

public class ClosestPalindrome {
	// @include
	long diff(long a, long b) { return a > b ? a - b : b - a; }

	static long find_closest_palindrome(long x) {
		// Make str a palindrome by mirroring the left half to the right half.
		String mirrored = mirrorLeftHalf(String.valueOf(x));
		StringBuilder str = new StringBuilder(mirrored);
	  
		long mirror_left = Long.valueOf(mirrored);
		int idx = (str.length() - 1) >> 1;
		if (mirror_left >= x) {
			// Subtract one from the left half.
			while (idx >= 0) {
				if (str.charAt(idx) == '0') {
					str.setCharAt(idx--, '9');
				} else {
					char c = str.charAt(idx);
					str.setCharAt(idx, (char) (c - 1));
					break;
				}
			}
			if (str.charAt(0) == '0') { // special case, make the whole string as "99...9".
				str.deleteCharAt(0); // removes the leading 0.
				fill(str, '9');
			}
		} else { // mirror_left < x.
			// Add one to the left half.
			while (idx >= 0) {
				if (str.charAt(idx) == '9') {
					str.setCharAt(idx--, '0');
				} else {
					char c = str.charAt(idx);
					str.setCharAt(idx, (char) (c + 1));
					break;
				}
			}
		}

	  // Make str a palindrome again by mirroring the left half to the right half.
	  mirrored = mirrorLeftHalf(str.toString());
	  return abs(x - mirror_left) < abs(x - Long.valueOf(mirrored)) ?
	              mirror_left : Long.valueOf(mirrored);
	}
	// @exclude

	static String mirrorLeftHalf(String s) {
		int half = s.length() / 2;
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < half; i++) {
			sb.setCharAt(sb.length() - 1 - i, s.charAt(i));
		}
		return sb.toString();
	}

	static boolean is_palindrome(long x) {
		String str = String.valueOf(x);
		for (int i = 0, j = str.length() - 1; i < j; ++i, --j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	static void check_answer(long x, long ans) {
		if (is_palindrome(x)) {
			assert ans == x;
			return;
		}
		long small = x - 1;
		while (is_palindrome(small) == false) {
			--small;
		}
		long big = x + 1;
		while (is_palindrome(big) == false) {
			++big;
		}
		if (x - small > big - x) {
			assert big - x == (ans > x ? ans - x : x - ans);
		} else {
			assert x - small == (ans > x ? ans - x : x - ans);
		}
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 100000; ++times) {
			long x;
			if (args.length == 1) {
				x = Integer.valueOf(args[0]);
			} else {
				x = gen.nextInt(10000000) + 1;
			}
			long ret = find_closest_palindrome(x);
			System.out.println(x + " " + ret);
			assert is_palindrome(ret);
			check_answer(x, ret);
		}
	}

}
