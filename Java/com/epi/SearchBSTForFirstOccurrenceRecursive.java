package com.epi;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchBSTForFirstOccurrenceRecursive {
    // @include
    public static <T extends Comparable<T>> BinaryTree<T> find_first_equal_k(
            BinaryTree<T> r, T k) {
        if (r == null) {
            return null;  // no match.
        } else if (r.getData().compareTo(k) == 0) {
            // Recursively search the left subtree for first one == k.
            BinaryTree<T> n = find_first_equal_k(r.getLeft(), k);
            return n != null ? n : r;
        }
        // Search left or right tree according to r->data and k.
        return find_first_equal_k(r.getData().compareTo(k) < 0 ? r.getRight() : r.getLeft(), k);
    }
    // @exclude

    public static void main(String[] args) {
        //    3
        //  2   6
        // 1   4 6
        BinaryTree<Integer> root = new BinaryTree<Integer>(3);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setLeft(new BinaryTree<Integer>(1));
        root.setRight(new BinaryTree<Integer>(6));
        root.getRight().setLeft(new BinaryTree<Integer>(4));
        root.getRight().setRight(new BinaryTree<Integer>(6));
        assert(find_first_equal_k(root, 7) == null);
        assert(find_first_equal_k(root, 6).getData().equals(6) &&
                find_first_equal_k(root, 6).getRight().getData().equals(6));
    }
}
