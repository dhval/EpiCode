// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

class Anagrams {
  // @include
  public static void find_anagrams(ArrayList<String> dictionary) {
    // Get the sorted string and then insert into hash table.
    HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
    for (String s : dictionary) {
      char[] sorted_ca = s.toCharArray();
      // Use sorted string as the hash code.
      Arrays.sort(sorted_ca);
      String sorted_str = new String(sorted_ca);
      if (!hash.containsKey(sorted_str)) {
        hash.put(sorted_str, new ArrayList<String>());
      }
      hash.get(sorted_str).add(s);
    }

    for (Map.Entry<String, ArrayList<String>> p : hash.entrySet()) {
      // Multiple strings with the same hash code => anagrams.
      if (p.getValue().size() >= 2) {
        // Output all strings.
        System.out.println(Arrays.toString(p.getValue().toArray()));
      }
    }
  }
  // @exclude

  private static String rand_string(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    ArrayList<String> dictionary = new ArrayList<String>();
    int n = rnd.nextInt(100000);
    HashSet<String> table = new HashSet<String>();
    for (int i = 0; i < n; ++i) {
      table.add(rand_string(rnd.nextInt(12) + 1));
    }
    for (String s : table) {
      dictionary.add(s);
    }
    find_anagrams(dictionary);
  }
}
