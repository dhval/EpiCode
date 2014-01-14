// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

// @include
class Indirect_sort {
  public static void indirect_sort(String file_name) throws Exception {
    // Store file records into A.
    Scanner ifs = null;
    ArrayList<Integer> A = new ArrayList<Integer>();
    try {
      ifs = new Scanner(new File(file_name));
      while (ifs.hasNextInt()) {
        A.add(ifs.nextInt());
      }
      ifs.close();
    } finally {
      ifs.close();
    }

    // Indirectly sort file.
    Collections.sort(A);

    // Output file.
    PrintWriter ofs = null;
    try {
      ofs = new PrintWriter(new FileWriter(file_name));
      for (Integer a : A) {
        ofs.println(a);
      }
      ofs.close();
    } finally {
      ofs.close();
    }
  }

  public static <T extends Comparable<T>> boolean is_sorted(Iterable<T> iterable) {
    Iterator<T> iter = iterable.iterator();
    if (!iter.hasNext()) {
      return true;
    }
    T t = iter.next();
    while (iter.hasNext()) {
      T t2 = iter.next();
      if (t.compareTo(t2) > 0) {
        return false;
      }
      t = t2;
    }
    return true;
  }
// @exclude

  public static void main(String[] args) throws Exception {
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println("times = " + times);
      int n = 0;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(10000) + 1;
      }
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; i++)
      {
        A.add(rnd.nextInt(999999 + 1));
      }

      PrintWriter ofs = null;
      try {
        ofs = new PrintWriter(new FileWriter("input.txt"));
        for (Integer a : A) {
          ofs.println(a);
        }
        ofs.close();
      } finally {
        ofs.close();
      }
      indirect_sort("input.txt");

      Scanner ifs = null;
      File input = new File("input.txt");
      try {
        ifs = new Scanner(input);
        A.clear();
        while (ifs.hasNextInt()) {
          A.add(ifs.nextInt());
        }
        ifs.close();
      } finally {
        ifs.close();
      }

      assert(is_sorted(A));
      input.delete();
    }
  }
// @include
}
