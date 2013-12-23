// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Random;

//@include
class CopyingPostingsList {

  // @include
  public static <T> pnode_t<T> copy_postings_list(pnode_t<T> L) {
    // Return empty list if L is nullptr.
    if (L == null) {
      return null;
    }

    // 1st stage: Copy the nodes from L.
    pnode_t<T> p = L;
    while (p != null) {
      pnode_t<T> temp = new pnode_t<T>(p.data, p.next, null);
      p.next = temp;
      p = temp.next;
    }

    // 2nd stage: Update the jump field.
    p = L;
    while (p != null) {
      if (p.jump != null) {
        p.next.jump = p.jump.next;
      }
      p = p.next.next;
    }

    // 3rd stage: Restore the next field.
    p = L;
    pnode_t<T> copied = p.next;
    while (p.next != null) {
      pnode_t<T> temp = p.next;
      p.next = temp.next;
      p = temp;
    }
    return copied;
  }

  // @exclude
  public static <T> void check_postings_list_equal(pnode_t<T> a, pnode_t<T> b) {
    while (a != null && b != null) {
      System.out.print(a.data + " ");
      assert(a.data == b.data);
      assert(a.jump == null &&
              b.jump == null ||
             (a.jump != null && b.jump != null && a.jump.data == b.jump.data));
      if (a.jump != null) {
        System.out.print(a.jump.data);
      }
      System.out.println("");
      a = a.next; b = b.next;
    }

    assert(a == null &&
           b == null);
  }

  public static void main(String[] argv) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }

      pnode_t<Integer> L = null;
      pnode_t<Integer> curr = L;
      for (int i = 0; i < n; ++i) {
        pnode_t<Integer> temp = new pnode_t<Integer>(i, null, null);
        if (L != null) {
          curr.next = temp;
          curr = temp;
        } else {
          curr = L = temp;
        }

        // Randomly assigned a jump node.
        int jump_num = (i > 0)?gen.nextInt(i):0;
        pnode_t<Integer> jump = L;
        while (jump_num-- != 0) {
          jump = jump.next;
        }
        temp.jump = jump;
      }

      pnode_t<Integer> copied = copy_postings_list(L);
      check_postings_list_equal(L, copied);
    }
  }
}
