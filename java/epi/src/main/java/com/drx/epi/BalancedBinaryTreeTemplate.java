package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BalancedBinaryTreeTemplate {
    // @include
    private static <T> int get_height(BinaryTree<T> n) {
        if(n == null) {
            return -1;
        }

        int l_height = get_height(n.getLeft());
        if(l_height == -2) {
            return -2; // left subtree is not balanced.
        }

        int r_height = get_height(n.getRight());
        if(r_height == -2) {
            return -2; // right subtree is not balanced.
        }

        if(Math.abs(l_height - r_height) > 1) {
            return -2; // current node n is not balanced.
        }
        return Math.max(l_height, r_height) + 1;
    }

    public static <T> boolean is_balanced_binary_tree(BinaryTree<T> n) {
        return get_height(n) != -2;
    }
    // @exclude

    public static void main(String[] args) {
        //  balanced binary tree test
        //      3
        //    2   5
        //  1    4 6
        BinaryTree<Integer> root = new BinaryTree<Integer>();
        root.setLeft(new BinaryTree<Integer>());
        root.getLeft().setLeft(new BinaryTree<Integer>());
        root.setRight(new BinaryTree<Integer>());
        root.getRight().setLeft(new BinaryTree<Integer>());
        root.getRight().setRight(new BinaryTree<Integer>());
        assert(is_balanced_binary_tree(root) == true);
        System.out.println(is_balanced_binary_tree(root));
        root = new BinaryTree<Integer>();
        root.setLeft(new BinaryTree<Integer>());
        root.getLeft().setLeft(new BinaryTree<Integer>());
        assert(is_balanced_binary_tree(root) == false);
        System.out.println(is_balanced_binary_tree(root));
    }
}
