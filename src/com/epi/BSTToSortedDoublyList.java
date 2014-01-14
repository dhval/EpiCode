package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BSTToSortedDoublyList {
    // @include
    // Transform a BST into a circular sorted doubly linked list in-place,
    // return the head of the list.
    public static <T> BinaryTree<T> BST_to_doubly_list(BinaryTree<T> n) {
        // Empty subtree.
        if (n == null) {
            return null;
        }

        // Recursively build the list from left and right subtrees.
        BinaryTree<T> l_head = BST_to_doubly_list(n.getLeft());
        BinaryTree<T> r_head = BST_to_doubly_list(n.getRight());

        // Append n to the list from left subtree.
        BinaryTree<T> l_tail = null;
        if (l_head != null) {
            l_tail = l_head.getLeft();
            l_tail.setRight(n);
            n.setLeft(l_tail);
            l_tail = n;
        } else {
            l_head = l_tail = n;
        }

        // Append the list from right subtree to n.
        BinaryTree<T> r_tail = null;
        if (r_head != null) {
            r_tail = r_head.getLeft();
            l_tail.setRight(r_head);
            r_head.setLeft(l_tail);
        } else {
            r_tail = l_tail;
        }
        r_tail.setRight(l_head);
        l_head.setLeft(r_tail);

        return l_head;
    }
    // @exclude

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1    4 6
        BinaryTree<Integer> root = new BinaryTree<Integer>(3);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setLeft(new BinaryTree<Integer>(1));
        root.setRight(new BinaryTree<Integer>(5));
        root.getRight().setLeft(new BinaryTree<Integer>(4));
        root.getRight().setRight(new BinaryTree<Integer>(6));
        BinaryTree<Integer> L = BST_to_doubly_list(root);
        BinaryTree<Integer> curr = L;
        int pre = Integer.MIN_VALUE;
        do {
            assert(pre <= curr.getData());
            System.out.println(curr.getData());
            pre = curr.getData();
            curr = curr.getRight();
        } while (curr != L);
    }
}
