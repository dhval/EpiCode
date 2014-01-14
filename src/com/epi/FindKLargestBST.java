package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class FindKLargestBST {
    // @include
    public static <T> List<T> find_k_largest_in_BST(BinaryTree<T> root,
                                    int k) {
        ArrayList<T> k_elements = new ArrayList<T>();
        find_k_largest_in_BST_helper(root, k, k_elements);
        return k_elements;
    }

    private static <T> void find_k_largest_in_BST_helper(BinaryTree<T> r,
                                      int k,
                                      List<T> k_elements) {
        // Performs reverse inorder traversal.
        if (r != null && k_elements.size() < k) {
            find_k_largest_in_BST_helper(r.getRight(), k, k_elements);
            if (k_elements.size() < k) {
                k_elements.add(r.getData());
                find_k_largest_in_BST_helper(r.getLeft(), k, k_elements);
            }
        }
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
        Random r = new Random();
        int k;
        if (args.length == 1) {
            k = Integer.parseInt(args[0]);
        } else {
            k = r.nextInt(6) + 1;
        }
        System.out.println("k = " + k);
        List<Integer> ans = find_k_largest_in_BST(root, k);
        System.out.println(ans);
        for (int i = 1; i < ans.size(); ++i) {
            assert(ans.get(i - 1) >= ans.get(i));
        }

    }
}
