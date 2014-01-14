package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RebuildBSTPreorderBetter {
    // @include
    public static <T extends Comparable<T>> BinaryTree<T> rebuild_BST_from_preorder(List<T> preorder) {
        ObjectWrapper<Integer> idx = new ObjectWrapper<Integer>(0);
        return rebuild_BST_from_preorder_helper(
                preorder, idx, Collections.min(preorder), Collections.max(preorder));
    }

    private static <T extends Comparable<T>> BinaryTree<T> rebuild_BST_from_preorder_helper(
            List<T> preorder, ObjectWrapper<Integer> idx, T min, T max) {
        if (idx.get() == preorder.size()) {
            return null;
        }

        T curr = preorder.get(idx.get());
        if (curr.compareTo(min) < 0 || curr.compareTo(max) > 0) {
            return null;
        }

        idx.set(idx.get() + 1);
        return new BinaryTree<T>(
            curr, rebuild_BST_from_preorder_helper(preorder, idx, min, curr),
                  rebuild_BST_from_preorder_helper(preorder, idx, curr, max));
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
