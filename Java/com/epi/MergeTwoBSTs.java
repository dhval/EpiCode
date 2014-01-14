package com.epi;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MergeTwoBSTs {
    // Build a BST from the (s + 1)-th to the e-th node in L.
    private static <T> BinaryTree<T> build_BST_from_sorted_doubly_list_helper(
            ObjectWrapper<BinaryTree<T>> L,
            int s,
            int e) {
        BinaryTree<T> curr = null;
        if (s < e) {
            int m = s + ((e - s) >> 1);
            BinaryTree<T> temp = build_BST_from_sorted_doubly_list_helper(L, s, m);
            curr = L.get();
            curr.setLeft(temp);
            L.set(L.get().getRight());
            curr.setRight(build_BST_from_sorted_doubly_list_helper(L, m + 1, e));
        }
        return curr;
    }

    private static <T> BinaryTree<T> build_BST_from_sorted_doubly_list(
            BinaryTree<T> L,
            int n) {
        return build_BST_from_sorted_doubly_list_helper(new ObjectWrapper<BinaryTree<T>>(L), 0, n);
    }

    // Transform a BST into a circular sorted doubly linked list in-place,
    // return the head of the list.
    private static <T> BinaryTree<T> BST_to_doubly_list(
            BinaryTree<T> n) {
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

    // Count the list length till end.
    private static <T> int count_len(BinaryTree<T> L) {
        int len = 0;
        while (L != null) {
            ++len;
            L = L.getRight();
        }
        return len;
    }

    // @include
    public static <T extends Comparable<T>> BinaryTree<T> merge_BSTs(
            BinaryTree<T> A,
            BinaryTree<T> B) {
        // Transform BSTs A and B into sorted doubly lists.
        A = BST_to_doubly_list(A);
        B = BST_to_doubly_list(B);
        A.getLeft().setRight(null);
        B.getLeft().setRight(null);
        A.setLeft(null);
        B.setLeft(null);
        int len_A = count_len(A);
        int len_B = count_len(B);
        return build_BST_from_sorted_doubly_list(merge_sorted_linked_lists(A, B),
                len_A + len_B);
    }

    // Merge two sorted linked lists, return the head of list.
    private static <T extends Comparable<T>> BinaryTree<T> merge_sorted_linked_lists(
            BinaryTree<T> A,
            BinaryTree<T> B) {
        ObjectWrapper<BinaryTree<T>> sorted_list_w = new ObjectWrapper<BinaryTree<T>>(null);
        ObjectWrapper<BinaryTree<T>> tail_w = new ObjectWrapper<BinaryTree<T>>(null);
        ObjectWrapper<BinaryTree<T>> A_w = new ObjectWrapper<BinaryTree<T>>(A);
        ObjectWrapper<BinaryTree<T>> B_w = new ObjectWrapper<BinaryTree<T>>(B);

        while (A_w.get() != null && B_w.get() != null) {
            append_node_and_advance(sorted_list_w, tail_w, A_w.get().getData().compareTo(B_w.get().getData()) < 0 ? A_w : B_w);
        }

        // Append the remaining of A.
        if (A_w.get() != null) {
            append_node(sorted_list_w, tail_w, A_w);
        }
        // Append the remaining of B.
        if (B_w.get() != null) {
            append_node(sorted_list_w, tail_w, B_w);
        }
        return sorted_list_w.get();
    }

    private static <T> void append_node_and_advance(ObjectWrapper<BinaryTree<T>> head,
                                                    ObjectWrapper<BinaryTree<T>> tail,
                                                    ObjectWrapper<BinaryTree<T>> n) {
        append_node(head, tail, n);
        n.set(n.get().getRight());  // advance n.
    }

    private static <T> void append_node(ObjectWrapper<BinaryTree<T>> head,
                                        ObjectWrapper<BinaryTree<T>> tail,
                                        ObjectWrapper<BinaryTree<T>> n) {
        if (head.get() != null) {
            tail.get().setRight(n.get()); n.get().setLeft(tail.get());
        } else {
            head.set(n.get());
        }
        tail.set(n.get());
    }
    // @exclude

    private static <T extends Comparable<T>> void print_BST_inorder(BinaryTree<T> n, T pre) {
        if (n != null) {
            print_BST_inorder(n.getLeft(), pre);
            assert(pre.compareTo(n.getData()) <= 0);
            System.out.print(n.getData() + " ");
            print_BST_inorder(n.getRight(), n.getData());
        }
    }

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1    4 6
        BinaryTree<Integer> L = new BinaryTree<Integer>(3);
        L.setLeft(new BinaryTree<Integer>(2));
        L.getLeft().setLeft(new BinaryTree<Integer>(1));
        L.setRight(new BinaryTree<Integer>(5));
        L.getRight().setLeft(new BinaryTree<Integer>(4));
        L.getRight().setRight(new BinaryTree<Integer>(6));
        //     7
        //   2   8
        // 0
        BinaryTree<Integer> R = new BinaryTree<Integer>(7);
        R.setLeft(new BinaryTree<Integer>(2));
        R.getLeft().setLeft(new BinaryTree<Integer>(0));
        R.setRight(new BinaryTree<Integer>(8));

        BinaryTree<Integer> root = merge_BSTs(L, R);
        // should output 0 1 2 2 3 4 5 6 7 8
        print_BST_inorder(root, Integer.MIN_VALUE);
    }
}
