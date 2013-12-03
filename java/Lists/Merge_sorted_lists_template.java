// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
import java.util.Random;

// used to implement pass-by-reference argument passing
// http://stackoverflow.com/questions/7884581/how-can-i-simulate-pass-by-reference-in-java
// @include
class Ref<T> {
  T value;

  Ref(T v) {
    value = v;
  }
}

class Merge_sorted_lists {
  public static <T> void append_node(Ref<node_t<T>> head, Ref<node_t<T>> tail,
      Ref<node_t<T>> n) {
    if (head.value != null) {
      tail.value.next = n.value;
    } else {
      head.value = n.value;
    }
    tail.value = n.value; // reset tail to the last node.
  }

  public static <T> void append_node_and_advance(Ref<node_t<T>> head,
      Ref<node_t<T>> tail, Ref<node_t<T>> n) {
    append_node(head, tail, n);
    n.value = n.value.next; // advance n.
  }

  public static <T extends Comparable<T>> node_t<T> merge_sorted_linked_lists(
      node_t<T> F, node_t<T> L) {
    Ref<node_t<T>> sorted_head = new Ref<node_t<T>>(null);
    Ref<node_t<T>> tail = new Ref<node_t<T>>(null);
    Ref<node_t<T>> RF = new Ref<node_t<T>>(F);
    Ref<node_t<T>> RL = new Ref<node_t<T>>(L);

    while (RF.value != null && RL.value != null) {
      append_node_and_advance(sorted_head, tail,
          RF.value.data.compareTo(RL.value.data) < 0 ? RF : RL);
    }

    // Append the remaining nodes of F.
    if (RF.value != null) {
      append_node(sorted_head, tail, RF);
    }

    // Append the remaining nodes of L.
    if (RL.value != null) {
      append_node(sorted_head, tail, RL);
    }

    return sorted_head.value;
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 10000; ++times) {
      node_t<Integer> F = null;
      node_t<Integer> L = null;
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(100);
        m = rnd.nextInt(100);
      }
      for (int i = n; i > 0; --i) {
        node_t<Integer> temp = new node_t<Integer>(i, null);
        temp.next = F;
        F = temp;
      }
      for (int j = m; j > 0; --j) {
        node_t<Integer> temp = new node_t<Integer>(j, null);
        temp.next = L;
        L = temp;
      }

      node_t<Integer> sorted_head = merge_sorted_linked_lists(F, L);
      int count = 0;
      int pre = Integer.MIN_VALUE;
      while (sorted_head != null) {
        assert (pre <= sorted_head.data);
        pre = sorted_head.data;
        sorted_head = sorted_head.next;
        ++count;
      }
      // Make sure the merged list have the same number of nodes as F and L.
      assert (count == n + m);
    }
  }
}
// @exclude
