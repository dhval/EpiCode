package com.drx.epi;

public class RegularExpression {
	// @include
	static boolean is_match_here(String r, String s) {
		// Case (1.)
		if (r.isEmpty()) {
			return true;
		}

		// Case (2) : ends with '$'.
		if ("$".equals(r)) {
			return s.isEmpty();
		}

		// Case (4.)
		if (r.length() >= 2 && r.charAt(1) == '*') {
			for (int i = 0; 
					i < s.length() && (r.charAt(0) == '.' || r.charAt(0) == s.charAt(i)); 
					++i) {
				if (is_match_here(r.substring(2), s.substring(i + 1))) {
					return true;
				}
			}
			return is_match_here(r.substring(2), s);
		}

		// Case (3.)
		return !s.isEmpty() && (r.charAt(0) == '.' || r.charAt(0) == s.charAt(0))
				&& is_match_here(r.substring(1), s.substring(1));
	}

	static boolean is_match(String r, String s) {
		// Case (2.) : starts with '^'.
		if (r.charAt(0) == '^') {
			return is_match_here(r.substring(1), s);
		}

		for (int i = 0; i <= s.length(); ++i) {
			if (is_match_here(r, s.substring(i))) {
				return true;
			}
		}
		return false;
	}
	// @exclude

	public static void main(String[] args) {
		assert (is_match(".", "a"));
		assert (is_match("a", "a"));
		assert (!is_match("a", "b"));
		assert (is_match("a.9", "aW9"));
		assert (!is_match("a.9", "aW19"));
		assert (is_match("^a.9", "aW9"));
		assert (!is_match("^a.9", "baW19"));
		assert (is_match(".*", "a"));
		assert (is_match(".*", ""));
		assert (is_match("c*", "c"));
		assert (!is_match("aa*", "c"));
		assert (is_match("ca*", "c"));
		assert (is_match(".*", "asdsdsa"));
		assert (is_match("9$", "xxxxW19"));

		assert (is_match(".*a", "ba"));

		assert (is_match(".*a", "ba"));
		assert (is_match("^a.*9$", "axxxxW19"));

		assert (is_match("^a.*W19$", "axxxxW19"));
		assert (is_match(".*a.*W19", "axxxxW19123"));
		assert (!is_match(".*b.*W19", "axxxxW19123"));
		assert (is_match("n.*", "n"));
		assert (is_match("a*n.*", "an"));
		assert (is_match("a*n.*", "ana"));
		assert (is_match("a*n.*W19", "anaxxxxW19123"));
		assert (is_match(".*a*n.*W19", "asdaaadnanaxxxxW19123"));
		assert (is_match(".*.*.a*n.*W19", "asdaaadnanaxxxxW19123"));
	}
}
