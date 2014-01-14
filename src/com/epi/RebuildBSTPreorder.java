package com.drx.epi;

import java.util.ArrayList;
import java.util.List;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPreorder {
    // @include
    // Given a preorder traversal of a BST, return its root.
    public static <T extends Comparable<T>> BinaryTree<T> rebuild_BST_from_preorder(List<T> preorder) {
        return rebuild_BST_from_preorder_helper(preorder, 0, preorder.size());
    }

    // Build a BST based on preorder[s : e - 1], return its root.
    private static <T extends Comparable<T>> BinaryTree<T> rebuild_BST_from_preorder_helper(
            List<T> preorder, int s, int e) {
        if (s < e) {
            int x = s + 1;
            while (x < e && preorder.get(x).compareTo(preorder.get(s)) < 0) {
                ++x;
            }
            return new BinaryTree<T>(
                    preorder.get(s),
                    rebuild_BST_from_preorder_helper(preorder, s + 1, x),
                    rebuild_BST_from_preorder_helper(preorder, x, e));
        }
        return null;
    }
    // @exclude

    private static <T extends Comparable<T>> void check_ans(BinaryTree<T> n, T pre) {
        if (n != null) {
            check_ans(n.getLeft(), pre);
            assert(pre.compareTo(n.getData()) <= 0);
            System.out.println(n.getData());
            check_ans(n.getRight(), n.getData());
        }
    }

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1    4  6
        // should output 1, 2, 3, 4, 5, 6
        // preorder [3, 2, 1, 5, 4, 6]
        ArrayList<Integer> preorder = new ArrayList<Integer>();
        preorder.add(3);
        preorder.add(2);
        preorder.add(1);
        preorder.add(5);
        preorder.add(4);
        preorder.add(6);
        BinaryTree<Integer> root = rebuild_BST_from_preorder(preorder);
        check_ans(root, Integer.MIN_VALUE);
    }
}
