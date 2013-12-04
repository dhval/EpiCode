package com.drx.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchPostingsListIterative {
    // @include
    public static void search_postings_list(NodeT L) {
        LinkedList<NodeT> s = new LinkedList<NodeT>();
        int order = 0;
        s.push(L);
        while(!s.isEmpty()) {
            NodeT curr = s.pop();
            if(curr != null && curr.getOrder() == -1) {
                curr.setOrder(order++);
                s.push(curr.getNext());
                s.push(curr.getJump());
            }
        }
    }
    // @exclude

    public static void main(String[] args) {
        NodeT L = null, curr = null;
        // Build a linked list L->1->2->3->4->5->nullptr.
        for(int i = 0; i < 5; ++i) {
            NodeT temp = new NodeT(-1, null, null);
            if(curr != null) {
                curr.setNext(temp);
                curr = temp;
            } else {
                curr = L = temp;
            }
        }

        L.setJump(null);                                      // no jump from 1
        L.getNext().setJump(L.getNext().getNext().getNext()); // 2's jump points to 4
        L.getNext().getNext().setJump(L);                     // 3's jump points to 1
        L.getNext().getNext().getNext().setJump(null);        // no jump from 4
        L.getNext().getNext().getNext().getNext().setJump(
                L.getNext().getNext().getNext().getNext());   // 5's jump points to 5
        NodeT temp = L;
        search_postings_list(L);
        // output the jump-first order, it should be 0, 1, 4, 2, 3
        assert(temp.getOrder() == 0);
        temp = temp.getNext();
        assert(temp.getOrder() == 1);
        temp = temp.getNext();
        assert(temp.getOrder() == 4);
        temp = temp.getNext();
        assert(temp.getOrder() == 2);
        temp = temp.getNext();
        assert(temp.getOrder() == 3);
    }
}
