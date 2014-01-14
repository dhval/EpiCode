package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABST {
    // @include
    public static <T extends Comparable<T>> boolean is_BST(BinaryTree<T> r, T min, T max) {
        return is_BST_helper(r, min, max);
    }

    private static <T extends Comparable<T>> boolean is_BST_helper(BinaryTree<T> r,
                       T lower,
                       T upper) {
        if (r == null) {
            return true;
        } else if (r.getData().compareTo(lower) < 0 || r.getData().compareTo(upper) > 0) {
            return false;
        }

        return is_BST_helper(r.getLeft(), lower, r.getData()) &&
                is_BST_helper(r.getRight(), r.getData(), upper);
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
        // should output true.
        assert(is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) == true);
        System.out.println(is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        //      10
        //    2   5
        //  1    4 6
        root.setData(10);
        // should output false.
        assert(!is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        // should output true.
        assert(is_BST(null, Integer.MIN_VALUE, Integer.MAX_VALUE) == true);
        System.out.println(is_BST(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
