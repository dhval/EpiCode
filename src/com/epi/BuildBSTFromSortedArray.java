package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BuildBSTFromSortedArray {
    // @include
    public static <T> BinaryTree<T> build_BST_from_sorted_array(List<T> A) {
        return build_BST_from_sorted_array_helper(A, 0, A.size());
    }

    // Build BST based on subarray A[start : end - 1].
    private static <T> BinaryTree<T> build_BST_from_sorted_array_helper(List<T> A,
                                                            int start,
                                                            int end) {
        if (start < end) {
            int mid = start + ((end - start) >> 1);
            return new BinaryTree<T>(
                A.get(mid), build_BST_from_sorted_array_helper(A, start, mid),
                                build_BST_from_sorted_array_helper(A, mid + 1, end));
        }
        return null;
    }
    // @exclude

    private static int traversal_check(BinaryTree<Integer> root, Integer target) {
        if (root != null) {
            target = traversal_check(root.getLeft(), target);
            assert(target .equals(root.getData()));
            ++target;
            target = traversal_check(root.getRight(), target);
        }
        return target;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            ArrayList<Integer> A = new ArrayList<Integer>();
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(1000) + 1;
            }
            for (int i = 0; i < n; ++i) {
                A.add(i);
            }
            BinaryTree<Integer> root = build_BST_from_sorted_array(A);
            int target = 0;
            traversal_check(root, target);
        }
    }
}
