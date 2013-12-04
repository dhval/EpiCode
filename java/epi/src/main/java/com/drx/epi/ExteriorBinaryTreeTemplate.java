package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ExteriorBinaryTreeTemplate {
    // @include
    private static <T> void left_boundary_b_tree(BinaryTree<T> n, boolean is_boundary) {
        if(n != null) {
            if(is_boundary || (n.getLeft() == null && n.getRight() == null)) {
                System.out.print(n.getData() + " ");
            }
            left_boundary_b_tree(n.getLeft(), is_boundary);
            left_boundary_b_tree(n.getRight(), is_boundary && n.getLeft() == null);
        }
    }

    private static <T> void right_boundary_b_tree(BinaryTree<T> n, boolean is_boundary) {
        if(n != null) {
            right_boundary_b_tree(n.getLeft(), is_boundary && n.getRight() == null);
            right_boundary_b_tree(n.getRight(), is_boundary);
            if(is_boundary || (n.getLeft() == null && n.getRight() == null)) {
                System.out.print(n.getData() + " ");
            }
        }
    }

    public static <T> void exterior_binary_tree(BinaryTree<T> root) {
        if(root != null) {
            System.out.print(root.getData() + " ");
            left_boundary_b_tree(root.getLeft(), true);
            right_boundary_b_tree(root.getRight(), true);
        }
    }
    // @exclude

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1  0 4 6
        //   -1 -2
        BinaryTree<Integer> root = new BinaryTree<Integer>(3, null, null);
        root.setLeft(new BinaryTree<Integer>(2, null, null));
        root.getLeft().setRight(new BinaryTree<Integer>(0, null, null));
        root.getLeft().getRight().setLeft(new BinaryTree<Integer>(-1, null, null));
        root.getLeft().getRight().setRight(new BinaryTree<Integer>(-2, null, null));
        root.getLeft().setLeft(new BinaryTree<Integer>(1, null, null));
        root.setRight(new BinaryTree<Integer>(5, null, null));
        root.getRight().setLeft(new BinaryTree<Integer>(4, null, null));
        root.getRight().setRight(new BinaryTree<Integer>(6, null, null));
        // should output 3 2 1 -1 -2 4 6 5
        exterior_binary_tree(root);
    }
}
