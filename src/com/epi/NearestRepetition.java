// @author Ivan Sharov

package com.epi;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

class NearestRepetition {
  private static String rand_String(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static int find_nearest_repetition(List<String> s) {
    HashMap<String, Integer> String_to_location = new HashMap<String, Integer>();
    int closest_dis = Integer.MAX_VALUE;
    for (int i = 0; i < s.size(); ++i) {
      if (String_to_location.containsKey(s.get(i)))
      {
        closest_dis = Math.min(closest_dis, i - String_to_location.get(s.get(i)));
      }
      String_to_location.put(s.get(i), i);
    }
    return closest_dis;
  }
  // @exclude

  // O(n^2) checking
  private static int check_answer(List<String> s) {
    int closest_dis = Integer.MAX_VALUE;
    for (int i = 0; i < s.size(); ++i) {
      for (int j = i + 1; j < s.size(); ++j) {
        if (s.get(i).equals(s.get(j))) {
          closest_dis = Math.min(closest_dis, j - i);
        }
      }
    }
    return closest_dis;
  }

  public static void main(String[] args) {
    List<String> A = Arrays.asList("foo", "bar", "widget", "foo", "widget", "widget", "adnan");
    assert(check_answer(A) == find_nearest_repetition(A));
    A = Arrays.asList("foo", "bar", "widget", "foo", "xyz", "widget", "bar", "adnan");
    assert(check_answer(A) == find_nearest_repetition(A));
    A = Arrays.asList("foo", "bar", "widget", "adnan");
    assert(check_answer(A) == find_nearest_repetition(A));
    A = Arrays.asList();
    assert(check_answer(A) == find_nearest_repetition(A));
    A = Arrays.asList("foo", "foo", "foo");
    assert(check_answer(A) == find_nearest_repetition(A));
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n = 0;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(10000) + 1;
      }
      List<String> s = new ArrayList<String>();
      for (int i = 0; i < n; ++i) {
        s.add(rand_String(rnd.nextInt(10) + 1));
      }
      assert(check_answer(s) == find_nearest_repetition(s));
    }
  }
}
