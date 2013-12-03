// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

// @include
class Checking_cycle_alternative {
  public static <T> node_t<T> has_cycle(node_t<T> head) {
    node_t<T> fast = head;
    node_t<T> slow = head;

    while (slow != null && slow.next != null && fast != null 
                        && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {  // there is a cycle.
        // Tries to find the start of the cycle.
        slow = head;
        // Both pointers advance at the same time.
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;  // slow is the start of cycle.
      }
    }
    return null;  // means no cycle.
  }
  // @exclude

  public static void main(String[] args) {
    node_t<Integer> L3 = new node_t<Integer>(3, null);
    node_t<Integer> L2 = new node_t<Integer>(2, L3);
    node_t<Integer> L1 = new node_t<Integer>(1, L2);

    // should output "L1 does not have cycle."
    assert(has_cycle(L1) == null);
    System.out.println("L1 " 
            + (has_cycle(L1) != null ? "has" : "does not have") + " cycle.");

    // make it a cycle
    L3.next = L2;
    // should output "L1 has cycle, located at node has value 2"
    assert(has_cycle(L1) != null);
    assert(has_cycle(L1).data == 2);
    node_t<Integer> temp = has_cycle(L1);
    if (temp != null) {
      System.out.println("L1 has cycle, located at node has value " 
              + temp.data);
    } else {
      System.out.println("L1 does not have cycle");
    }
  }
}
