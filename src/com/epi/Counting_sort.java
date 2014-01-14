// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

// @include
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Collections;

class Person implements Comparable<Person> {
  public Integer key;
  public String name;

  public Person(Integer k, String n) {
    key = k;
    name = n;
  }

  // Hash function for Person.
  @Override
  public int hashCode() {
    return key.hashCode() ^ name.hashCode();
  }

  @Override
  public int compareTo(Person p) {
    return key.compareTo(p.key);
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof Person && ((Person)o).key.equals(key)) {
      return true;
    } else {
      return false;
    }
  }
}

class Counting_sort {
  public static void counting_sort(ArrayList<Person> people) {
    HashMap<Integer, Integer> key_to_count = new HashMap<Integer, Integer>();
    for (Person p : people) {
      if (key_to_count.containsKey(p.key)) {
        key_to_count.put(p.key, key_to_count.get(p.key) + 1);
      } else {
        key_to_count.put(p.key, 1);
      }
    }
    HashMap<Integer, Integer> key_to_offset = new HashMap<Integer, Integer>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> kc : key_to_count.entrySet()) {
      key_to_offset.put(kc.getKey(), offset);
      offset += kc.getValue();
    }

    while (!key_to_offset.isEmpty()) {
      Map.Entry<Integer, Integer> from = key_to_offset.entrySet().iterator().next();
      Integer to_key = people.get(from.getValue()).key;
      Integer to_value = key_to_offset.get(to_key);
      Collections.swap(people, from.getValue(), to_value);
      // Use key_to_count to see when we are finished with a particular key.
      Integer count = key_to_count.get(to_key) - 1;
      key_to_count.put(to_key, count);
      if (count > 0) {
        key_to_offset.put(to_key, to_value + 1);
      } else {
        key_to_offset.remove(to_key);
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
    for (int times = 0; times < 1000; ++times) {
      int size = 0;
      if (args.length == 1 || args.length == 2) {
        size = Integer.parseInt(args[0]);
      } else {
        size = rnd.nextInt(10000) + 1;
      }
      int k = 0;
      if (args.length == 2) {
        k = Integer.parseInt(args[1]);
      } else {
        k = rnd.nextInt(size) + 1;
      }
      ArrayList<Person> people = new ArrayList<Person>();
      for (int i = 0; i < size; ++i) {
        people.add(new Person(rnd.nextInt(k), rand_string(rnd.nextInt(10) + 1)));
      }
      HashSet<Integer> key_set = new HashSet<Integer>();
      for (Person p : people) {
        key_set.add(p.key);
      }

      counting_sort(people);

      // Check the correctness of sorting.
      int diff_count = 1;
      for (int i = 1; i < people.size(); ++i) {
        if (!people.get(i).equals(people.get(i - 1))) {
          ++diff_count;
        }
      }
      assert(diff_count == key_set.size());
    }
  }
// @include
}
