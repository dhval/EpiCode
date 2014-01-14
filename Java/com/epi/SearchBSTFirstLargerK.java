package com.epi;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchBSTFirstLargerK {
    // @include
    public static <T extends Comparable<T>> BinaryTree<T> find_first_larger_k_with_k_exist(
            BinaryTree<T> r, T k) {
        boolean found_k = false;
        BinaryTree<T> curr = r;
        BinaryTree<T> first = null;

        while (curr != null) {
            if (curr.getData().compareTo(k) == 0) {
                found_k = true;
                curr = curr.getRight();
            } else if (curr.getData().compareTo(k) > 0) {
                first = curr;
                curr = curr.getLeft();
            } else {  // curr.getData().compareTo(k) < 0
                curr = curr.getRight();
            }
        }
        return found_k ? first : null;
    }
    // @exclude

    public static void main(String[] args) {
        //    3
        //  2   5
        // 1   4 7
        BinaryTree<Integer> root = new BinaryTree<Integer>(3);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setLeft(new BinaryTree<Integer>(1));
        root.setRight(new BinaryTree<Integer>(5));
        root.getRight().setLeft(new BinaryTree<Integer>(4));
        root.getRight().setRight(new BinaryTree<Integer>(7));
        assert(find_first_larger_k_with_k_exist(root, 1) == root.getLeft());
        assert(find_first_larger_k_with_k_exist(root, 5) == root.getRight().getRight());
        assert(find_first_larger_k_with_k_exist(root, 6) == null);
        assert(find_first_larger_k_with_k_exist(root, 7) == null);
    }
}
