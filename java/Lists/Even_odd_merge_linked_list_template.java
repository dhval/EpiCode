// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
import java.util.Random;

class Even_odd_merge_linked_list_template {
  // @include  
  public static <T> node_t<T> even_odd_merge(node_t<T> L) {
    node_t<T> odd = (L != null) ? L.next : null;
    node_t<T> odd_curr = odd;
    node_t<T> pre_even_curr = null, even_curr = L;
  
    while (even_curr != null && odd_curr != null) {
      even_curr.next = odd_curr.next;
      pre_even_curr = even_curr;
      even_curr = even_curr.next;
      if (even_curr != null) {
        odd_curr.next = even_curr.next;
        odd_curr = odd_curr.next;
      }
    }
  
    // Odd number of nodes.
    if (even_curr != null) {
      pre_even_curr = even_curr;
    }
    // Prevents empty list.
    if (pre_even_curr != null) {
      pre_even_curr.next = odd;
    }    
    return L;
  }
  // @exclude
  
  public static void main(String[] argv) {
    // input a list in reverse order.
    Random gen = new Random();
    node_t<Integer> head = null;
    int n = 0;
    if (argv.length > 1) {
      for (int i = 0; i < argv.length; ++i) {
        node_t<Integer> curr =
            new node_t<Integer>(Integer.parseInt(argv[i]), null);
        curr.next = head;
        head = curr;
      }
    } else {
      if (argv.length == 1) {
        n = Integer.parseInt(argv[0]);
      } else {        
        n = gen.nextInt(1000) + 1;
      }
      for (int i = n - 1; i >= 0; --i) {
        node_t<Integer> curr = new node_t<Integer>(i, null);
        curr.next = head;
        head = curr;
      }
    }
    node_t<Integer> ans = even_odd_merge(head);
    int x = 0;
    while (ans != null) {
      if (argv.length <= 1) {
        assert(x == ans.data);
        x += 2;
        if (x >= n) {
          x = 1;
        }
      }
      System.out.println(ans.data);
      ans = ans.next;
    }    
  }
}