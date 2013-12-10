// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.drx.epi;

class Palindrome_linked_list_template {

  // @include
  public static <T> boolean is_linked_list_a_palindrome(node_t<T> L) {
    // Find the middle point of L if L is odd length,
    // and right-middle point if L is even length.
    node_t<T> slow = L, fast = L;
    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        fast = fast.next; slow = slow.next;
      }
    }

    // Compare the first half and reversed second half lists.
    node_t<T> reverse =
            Reverse_linked_list_iterative_template.reverse_linked_list(slow);
    while (reverse != null && L != null) {
      if (reverse.data != L.data) {
        return false;
      }
      reverse = reverse.next; L = L.next;
    }
    return true;
  }
  // @exclude

  public static <T> void print_list(node_t<T> L) {
    while (L != null) {
      System.out.print(L.data + " ");
      L = L.next;
    }
    System.out.println("");
  }

  public static void main(String[] argv) {
    node_t<Integer> head = null;

    if (argv.length > 1) {
      //Input the node's value in reverse order.
      for (int i = 0; i < argv.length; i++) {
        node_t<Integer> curr =
            new node_t<Integer>(Integer.parseInt(argv[i]), head);
        head = curr;
      }
      System.out.println(((is_linked_list_a_palindrome(head)) ? "Yes" : "No"));
    } else {
      // A link list is a palindrome.
      for (int i = 6; i >= 1; --i) {
        node_t<Integer> curr =
            new node_t<Integer>(1, head);
        head = curr;
      }
      assert(is_linked_list_a_palindrome(head) == true);

      // Still a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        node_t<Integer> curr =
            new node_t<Integer>(1, head);
        head = curr;
      }
      head.next.next.data = 3;
      assert(is_linked_list_a_palindrome(head) == true);
      // Not a palindrome linked list.
      head = null;
      for (int i = 5; i >= 1; --i) {
        node_t<Integer> curr =
            new node_t<Integer>(i, head);
        head = curr;
      }
      assert(is_linked_list_a_palindrome(head) == false);
    }
  }
}
